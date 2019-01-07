package joker.persona.ngrocken.kngdancetrack;

import android.os.Bundle;

import joker.persona.ngrocken.kngdancetrack.util.ActivityTemplate;

public class MainActivity extends ActivityTemplate {

    private HomeFragment homeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        homeFragment = new HomeFragment();

        showFragment(R.id.main_fragment_container, homeFragment);
    }


}
