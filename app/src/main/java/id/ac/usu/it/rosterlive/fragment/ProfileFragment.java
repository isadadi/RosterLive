package id.ac.usu.it.rosterlive.fragment;


import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import id.ac.usu.it.rosterlive.LoginActivity;
import id.ac.usu.it.rosterlive.R;
import id.ac.usu.it.rosterlive.helper.SQLiteHandler;
import id.ac.usu.it.rosterlive.utilities.SessionManager;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {
    @BindView(R.id.tv_nim)
    TextView tvNim;

    @BindView(R.id.tv_name)
    TextView tvName;

    @BindView(R.id.tv_jurusan)
    TextView tvJurusan;

    @BindView(R.id.b_logout)
    Button bLogout;

    @BindView(R.id.profil_image)
    CircleImageView imgProfile;

    @BindView(R.id.pb_profile_image)
    ProgressBar pbProfileImage;

    private SQLiteHandler db;
    private SessionManager session;


    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        ButterKnife.bind(this, view);

        // SqLite database handler
        db = new SQLiteHandler(view.getContext());

        // session manager
        session = new SessionManager(view.getContext());

        // Fetching user details from sqlite
        HashMap<String, String> user = db.getUserDetails();

        tvNim.setText(user.get("username"));
        tvName.setText(user.get("name"));
        tvJurusan.setText(user.get("jurusan"));

        String username = user.get("username");

        String url_image = "https://portal.usu.ac.id/photos/" + username + ".jpg";
        Glide.with(view.getContext())
                .load(url_image)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        pbProfileImage.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        pbProfileImage.setVisibility(View.GONE);
                        return false;
                    }
                })
                .into(imgProfile);

        // Inflate the layout for this fragment
        return view;
    }


    @OnClick(R.id.b_logout)
    public void bLogoutClick() {
        logoutUser();
    }

    /**
     * Logging out the user. Will set isLoggedIn flag to false in shared
     * preferences Clears the user data from sqlite users table
     */
    private void logoutUser() {
        session.setLogin(false);

        db.deleteUsers();

        // Launching the login activity
        Intent intent = new Intent(getContext(), LoginActivity.class);
        startActivity(intent);
        getActivity().finish();
    }
}
