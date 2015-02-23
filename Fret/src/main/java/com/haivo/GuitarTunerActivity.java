/**
 * Sample code for "Making Musical Apps" by Peter Brinkmann
 * http://shop.oreilly.com/product/0636920022503.do
 */

package com.haivo;

import android.os.CountDownTimer;
import com.haivo.model.PitchItem;
import com.haivo.hailibrary.activities.BaseActivity;
import java.io.File;
import java.io.IOException;

import java.sql.Time;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import org.puredata.android.io.AudioParameters;
import org.puredata.android.service.PdService;
import org.puredata.android.utils.PdUiDispatcher;
import org.puredata.core.PdBase;
import org.puredata.core.PdListener;
import org.puredata.core.utils.IoUtils;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

public class GuitarTunerActivity extends BaseActivity {

    private static final String TAG = "GuitarTuner";
    private PdUiDispatcher dispatcher;
    private TextView pitchLabel;
    private TextView pitchNote;
    private TextView mTxtScore;
    private int mCurrentScore = 0;
    private PdService pdService = null;
    private ImageView pitchImage;
    private List<PitchItem> mPitchItems;
    private PitchItem mCurrentPitch;
    private final int NOISE_THRESHOLD = 200;
    private CountDownTimer mTimer;

    public static void launch(Activity activity) {
        final Intent intent = new Intent(activity, GuitarTunerActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        activity.startActivity(intent);
    }

    private final ServiceConnection pdConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            pdService = ((PdService.PdBinder) service).getService();
            try {
                initPd();
                loadPatch();
            } catch (IOException e) {
                Log.e(TAG, e.toString());
                finish();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            // this method will never be called
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initGui();
        initSystemServices();
        bindService(new Intent(this, PdService.class), pdConnection, BIND_AUTO_CREATE);
    }

    private void initData() {
        final PitchItem[] arrayOfPitches = PitchItem.class.getEnumConstants();
        mPitchItems = Arrays.asList(arrayOfPitches);
        final PitchItem ePitch = mPitchItems.get(0);
        pitchNote.setText(whichNote(ePitch.getFrequency()));
        randomlyGenerateNewPitch();
        mTimer = new CountDownTimer(NOISE_THRESHOLD, 10) {
            public void onTick(long millisUntilFinished) {
            }

            public void onFinish() {
                randomlyGenerateNewPitch();
                addPositivePoint();
            }
        };
    }

    private void randomlyGenerateNewPitch() {
        Random rand = new Random(System.currentTimeMillis());
        final int randomInteger = rand.nextInt(PitchItem.getMaxNumber() - PitchItem.getMinNumber())
            + PitchItem.getMinNumber();
        triggerNote(randomInteger);
        mCurrentPitch = mPitchItems.get(randomInteger);
        //triggerNote(mCurrentPitch.getFrequency()5);
        pitchImage.setImageResource(mCurrentPitch.getImageResId());
        //triggerNote(mCurrentPitch.getFrequency());
    }

    private void checkInput(int inputFrequency) {
        mTimer.cancel();
        if (inputFrequency == mCurrentPitch.getFrequency()) {
            mTimer.start();
        }
    }

    private void addPositivePoint() {
        mCurrentScore++;
        mTxtScore.setText(String.valueOf(mCurrentScore));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbindService(pdConnection);
    }

    private void initGui() {
        setContentView(R.layout.main);

        pitchLabel = (TextView) findViewById(R.id.pitch_label);
        pitchNote = (TextView) findViewById(R.id.pitch_note);
        pitchImage = (ImageView) findViewById(R.id.Image_pitch_image);
        mTxtScore = (TextView) findViewById(R.id.TextView_score);
        mTxtScore.setText(String.valueOf(mCurrentScore));
        pitchLabel.setText("So quiet!");
        pitchNote.setText("Play something dude!");
        pitchImage.setBackgroundResource(R.drawable.n40);
        initData();
    }

    private void initPd() throws IOException {
        // Configure the audio glue
        AudioParameters.init(this);
        int sampleRate = AudioParameters.suggestSampleRate();
        pdService.initAudio(sampleRate, 1, 2, 10.0f);
        start();

        // Create and install the dispatcher
        dispatcher = new PdUiDispatcher();
        PdBase.setReceiver(dispatcher);
        dispatcher.addListener("pitch", new PdListener.Adapter() {
            @Override
            public void receiveFloat(String source, final float x) {
                pitchLabel.setText(String.valueOf(x));
                final String note = whichNote(x);
                if (!note.isEmpty()) {
                    pitchNote.setText(whichNote(x));
                }
                final int roundedValue = Math.round(x);
                checkInput(roundedValue);
            }
        });
    }

    private void start() {
        if (!pdService.isRunning()) {
            Intent intent = new Intent(GuitarTunerActivity.this, GuitarTunerActivity.class);
            pdService.startAudio(intent, R.drawable.icon, "GuitarTuner", "Return to GuitarTuner.");
        }
    }

    private void loadPatch() throws IOException {
        File dir = getFilesDir();
        IoUtils.extractZipResource(getResources().openRawResource(R.raw.tuner), dir, true);
        File patchFile = new File(dir, "tuner.pd");
        PdBase.openPatch(patchFile.getAbsolutePath());
    }

    private void initSystemServices() {
        TelephonyManager telephonyManager =
            (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        telephonyManager.listen(new PhoneStateListener() {
            @Override
            public void onCallStateChanged(int state, String incomingNumber) {
                if (pdService == null) return;
                if (state == TelephonyManager.CALL_STATE_IDLE) {
                    start();
                } else {
                    pdService.stopAudio();
                }
            }
        }, PhoneStateListener.LISTEN_CALL_STATE);
    }

    private void triggerNote(int n) {
        PdBase.sendFloat("midinote", n);
        PdBase.sendBang("trigger");
    }

    private String whichNote(float frequency) {
        final int roundedValue = Math.round(frequency);
        switch (roundedValue) {
            case 40:
                return "1st E";
            case 41:
                return "1st F";
            case 42:
                return "1st F♯";
            case 43:
                return "1st G";
            case 44:
                return "1st G♯";
            case 45:
                return "1st A";
            case 46:
                return "1st A♯";
            case 47:
                return "1st B";
            case 48:
                return "1st C";
            case 49:
                return "1st C♯";
            case 50:
                return "1st D";
            case 51:
                return "1st D#";
            case 52:
                return "2nd E";
            case 53:
                return "2nd F";
            case 54:
                return "2nd F♯";
            case 55:
                return "2nd G";
            case 56:
                return "2nd G♯";
            case 57:
                return "2nd A";
            case 58:
                return "2nd A♯";
            case 59:
                return "2nd B";
            case 60:
                return "2nd C";
            case 61:
                return "2nd C#";
            case 62:
                return "2nd D";
            case 63:
                return "2nd D♯";
            case 64:
                return "3rd E";
            case 65:
                return "3rd F";
            case 66:
                return "3rd F♯";
            case 67:
                return "3rd G";
            case 68:
                return "3rd G#";
            default:
                return "";
        }
    }
}