package site.gemus.app;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import site.gemus.rxeventbusannotation.Subscribe;
import site.gemus.rxeventbusannotation.ThreadMode;

/**
 * A simple {@link Fragment} subclass.
 */
public class FirstFragment extends Fragment {
    private TextView mTextView;
    public FirstFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_one, container, false);
        mTextView = (TextView) view.findViewById(R.id.textview);
        return view;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageHandle(String s) {
        mTextView.setText(s);
    }
}
