package com.example.final_work;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

import com.example.final_work.databinding.FragmentVideoBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link VideoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VideoFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private FragmentVideoBinding binding;
    private VideoView videoView;
    private Button playButton, stopButton, replayButton,endButton;
    private MediaController mediaController;

    public VideoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment VideoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static VideoFragment newInstance(String param1, String param2) {
        VideoFragment fragment = new VideoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       binding=FragmentVideoBinding.inflate(inflater,container,false);
       View root=binding.getRoot();

       videoView=binding.videoViewFullScreen;
       playButton=binding.playFullScreen;
       stopButton=binding.stopFullScreen;
       replayButton=binding.replayFullScreen;
       endButton=binding.endFullScreen;
       mediaController=new MediaController(getActivity());

       videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
           @Override
           public void onPrepared(MediaPlayer mediaPlayer) {
               mediaPlayer.setOnInfoListener(new MediaPlayer.OnInfoListener() {
                   @Override
                   public boolean onInfo(MediaPlayer mediaPlayer, int i, int i1) {
                       if(i==MediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START)
                           videoView.setBackgroundColor(Color.TRANSPARENT);
                       return true;
                   }

               });
           }
       });

        playButton.setOnClickListener(new VideoFragment.customizeClick());
        stopButton.setOnClickListener(new VideoFragment.customizeClick());
        replayButton.setOnClickListener(new VideoFragment.customizeClick());
        endButton.setOnClickListener(new VideoFragment.customizeClick());
       return root;
    }


    class customizeClick implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            String uri = "android.resource://" + getActivity().getPackageName() + "/" + R.raw.video;
            videoView.setVideoURI(Uri.parse(uri));
            mediaController.setMediaPlayer(videoView);
            videoView.setMediaController(mediaController);
            if (view == playButton) {
                videoView.start();
            } else if (view == stopButton) {
                videoView.stopPlayback();
            } else if (view == replayButton) {
                videoView.resume();
            }
            else if(view==endButton){
                videoView.stopPlayback();
                videoView.setBackgroundColor(getResources().getColor(R.color.white));
            }
        }
    }
}