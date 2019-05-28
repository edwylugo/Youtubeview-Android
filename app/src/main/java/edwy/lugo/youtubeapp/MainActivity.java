package edwy.lugo.youtubeapp;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class MainActivity extends YouTubeBaseActivity
        implements YouTubePlayer.OnInitializedListener {

    private YouTubePlayerView youTubePlayerView;
    private static final String GOOGLE_API_KEY="AIzaSyB2IPlMGNc-HjGjUyRzkOTUrYZsEu3YfOM";

    private YouTubePlayer.PlaybackEventListener playbackEventListener; //monitorar eventos do vídeo
    private YouTubePlayer.PlayerStateChangeListener playerStateChangeListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playerStateChangeListener = new YouTubePlayer.PlayerStateChangeListener() {
            @Override
            public void onLoading() {

                exibirMensagem(MainActivity.this, "Vídeo Carregando");

            }

            @Override
            public void onLoaded(String s) {

                exibirMensagem(MainActivity.this, "Vídeo Carregado");

            }

            @Override
            public void onAdStarted() {

                exibirMensagem(MainActivity.this, "Propaganda iniciou");
            }

            @Override
            public void onVideoStarted() {
                exibirMensagem(MainActivity.this, "Video está começando");

            }

            @Override
            public void onVideoEnded() {
                exibirMensagem(MainActivity.this, "Vídeo chegou ao final");
            }

            @Override
            public void onError(YouTubePlayer.ErrorReason errorReason) {
                exibirMensagem(MainActivity.this, "Erro ao recuperar eventos de carregamento!");
            }
        };

        playbackEventListener = new YouTubePlayer.PlaybackEventListener() {
            @Override
            public void onPlaying() {
                exibirMensagem(MainActivity.this,"Video executando");
            }

            @Override
            public void onPaused() {
                exibirMensagem(MainActivity.this,"Video pausado");
            }

            @Override
            public void onStopped() {
                exibirMensagem(MainActivity.this,"Video parado");
            }

            @Override
            public void onBuffering(boolean b) {
                exibirMensagem(MainActivity.this,"Video pré-carregando");
            }

            @Override
            public void onSeekTo(int i) {
                exibirMensagem(MainActivity.this,"Movimentando SeekBar");
            }
        };

        youTubePlayerView = findViewById(R.id.viewYoutubePlayer);
        youTubePlayerView.initialize(GOOGLE_API_KEY, this );




    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean foiRestaurado) {

        exibirMensagem(this,"Player iniciado com sucesso!");

 //       youTubePlayer.loadVideo("wYcRaGsxM38"); //Carrega o vídeo automaticamente
        Log.i("estado_player","estado: " + foiRestaurado);


//        youTubePlayer.setPlaybackEventListener(playbackEventListener);
        youTubePlayer.setPlayerStateChangeListener(playerStateChangeListener);


        if (!foiRestaurado) {
//            youTubePlayer.cueVideo("wYcRaGsxM38"); //Carrega o vídeo preparado para execução
            youTubePlayer.cuePlaylist("PLBA57K2L2RIIfuOmp9T4YL0Sjpnvg-DH3"); //Adiciona uma playlist do Youtube
        }


    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        exibirMensagem(this,"Erro ao iniciar o Player!" + youTubeInitializationResult.toString());
    }

    private void exibirMensagem(Activity activity, String texto){
        Toast.makeText(activity, texto, Toast.LENGTH_SHORT).show();
    }
}
