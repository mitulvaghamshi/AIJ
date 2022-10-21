package me.mitul.aij.aij;

import android.os.Bundle;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import me.mitul.aij.utils.PlaceHolderFragment;
import me.mitul.aij.R;

public class AijExplorerActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore_aij);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, PlaceHolderFragment.newInstance(getIntent().getIntExtra(getString(R.string.item_id), 0)));
        transaction.commit();
    }
}
