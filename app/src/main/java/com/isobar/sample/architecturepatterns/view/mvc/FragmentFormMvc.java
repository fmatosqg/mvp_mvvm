package com.isobar.sample.architecturepatterns.view.mvc;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import com.isobar.sample.architecturepatterns.R;
import com.isobar.sample.architecturepatterns.model.Person;
import com.isobar.sample.architecturepatterns.model.PersonDao;
import com.isobar.sample.architecturepatterns.view.common.CommonFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by fabio.goncalves on 12/01/2017.
 */
public class FragmentFormMvc extends CommonFragment {

    public static final String BUNDLE_ARGUMENT_PERSON_PK = "BUNDLE_ARGUMENT_PERSON_PK";


    @BindView(R.id.form_name)
    EditText editName;

    @BindView(R.id.form_email)
    EditText editEmail;

    @BindView(R.id.form_password)
    EditText editPassword;

    @BindView(R.id.form_age)
    EditText editAge;

    @BindView(R.id.form_reminder)
    EditText editReminder;

    @BindView(R.id.form_save_button)
    Button saveButton;

    private Person person;
    private PersonDao personDao;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_user_form, container, false);

        ButterKnife.bind(this, view);

        Bundle bundle = null;

        if (savedInstanceState == null) {
            bundle = getArguments();
        } else {
            bundle = savedInstanceState;
        }

        Integer pk = null;
        if ( bundle != null ) {
            pk = bundle.getInt(BUNDLE_ARGUMENT_PERSON_PK);
        }

        if (pk != null ) {

            personDao = PersonDao.getInstance();
            person = personDao.queryId(pk);

            editName.setText(person.name);
            editEmail.setText(person.email);
            editAge.setText(Integer.toString(person.age));
            editReminder.setText(person.reminder);

            saveButton.setEnabled(true);
        }

        return view;
    }

    @OnClick(R.id.form_save_button)
    void saveForm() {

        person.name = editName.getText().toString();
        person.email = editEmail.getText().toString();

        personDao.updatePerson(person);

        getActivity().getSupportFragmentManager().popBackStack();
    }

    public static void createAndOpen(FragmentManager supportFragmentManager, Person person) {

        Fragment fragment = new FragmentFormMvc();

        if (person != null) {
            Bundle bundle = new Bundle();
            bundle.putInt(BUNDLE_ARGUMENT_PERSON_PK, person.pk);
            fragment.setArguments(bundle);
        }

        createAndOpen(supportFragmentManager,fragment);
    }
}
