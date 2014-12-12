package edu.mobicom.lifeplus;

import java.util.ArrayList;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass. Activities that contain this fragment
 * must implement the
 * {@link EditDailyQuestFragment.OnFragmentInteractionListener} interface to
 * handle interaction events. Use the {@link EditDailyQuestFragment#newInstance}
 * factory method to create an instance of this fragment.
 *
 */
public class EditDailyQuestFragment extends Fragment {
	// TODO: Rename parameter arguments, choose names that match
	// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
	private static final String ARG_PARAM1 = "param1";
	private static final String ARG_PARAM2 = "param2";

	// TODO: Rename and change types of parameters
	private String mParam1;
	private String mParam2;

	private OnFragmentInteractionListener mListener;

	/**
	 * Use this factory method to create a new instance of this fragment using
	 * the provided parameters.
	 *
	 * @param param1
	 *            Parameter 1.
	 * @param param2
	 *            Parameter 2.
	 * @return A new instance of fragment EditItemFragment.
	 */
	// TODO: Rename and change types and number of parameters
	public static EditDailyQuestFragment newInstance(String param1,
			String param2) {
		EditDailyQuestFragment fragment = new EditDailyQuestFragment();
		Bundle args = new Bundle();
		args.putString(ARG_PARAM1, param1);
		args.putString(ARG_PARAM2, param2);
		fragment.setArguments(args);
		return fragment;
	}

	public EditDailyQuestFragment() {
		// Required empty public constructor
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
		// Inflate the layout for this fragment
		View v = inflater.inflate(R.layout.fragment_edit_daily_quest,
				container, false);

		final DatabaseManager db = new DatabaseManager(getActivity(),
				"LifePlusTest", null, 1);
		final EditText etName = (EditText) v
				.findViewById(R.id.et_edit_daily_name);
		final EditText etDesc = (EditText) v
				.findViewById(R.id.et_edit_daily_desc);
		final EditText etTime = (EditText) v
				.findViewById(R.id.et_edit_daily_time);
		TextView tvStatus = (TextView) v
				.findViewById(R.id.tv_edit_daily_StatusUpdate);

		Task temp = null;
		for (Task t : db.getDailyQuests())
			if (t.getID() == Integer.parseInt(mParam1))
				temp = t;

		etName.setText(temp.getName());
		etDesc.setText(temp.getDesc());
		etTime.setText(temp.getTime().substring(0, temp.getTime().length() - 1));
		if (temp.isChecked() == true)
			tvStatus.setText("Finished");
		else
			tvStatus.setText("Ongoing");
		Button buttonDone = (Button) v
				.findViewById(R.id.button_edit_quest_done);
		buttonDone.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String name = etName.getText().toString();
				String desc = etDesc.getText().toString();

				if (name.isEmpty())
					Toast.makeText(getActivity(),
							"Please enter a name for the daily quest.",
							Toast.LENGTH_SHORT).show();
				else if (desc.isEmpty())
					Toast.makeText(getActivity(),
							"Please enter a description for the daily quest.",
							Toast.LENGTH_SHORT).show();
				else {

					Task editedQuest = new Task(name, desc, etTime.getText()
							.toString(), 1, false);
					DatabaseManager db = new DatabaseManager(getActivity(),
							"LifePlusTest", null, 1);

					db.editTask(Integer.parseInt(mParam1), editedQuest);

					getActivity()
							.getFragmentManager()
							.beginTransaction()
							.replace(
									R.id.container,
									CustomListFragment.newInstance(1,
											db.getDailyQuests())).commit();
				}
			}
		});

		return v;
	}

	// TODO: Rename method, update argument and hook method into UI event
	public void onButtonPressed(Uri uri) {
		if (mListener != null) {
			mListener.onFragmentInteraction(uri);
		}
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			mListener = new OnFragmentInteractionListener() {

				@Override
				public void onFragmentInteraction(Uri uri) {
					// TODO Auto-generated method stub

				}
			};
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement OnFragmentInteractionListener");
		}
	}

	@Override
	public void onDetach() {
		super.onDetach();
		mListener = null;
	}

	/**
	 * This interface must be implemented by activities that contain this
	 * fragment to allow an interaction in this fragment to be communicated to
	 * the activity and potentially other fragments contained in that activity.
	 * <p>
	 * See the Android Training lesson <a href=
	 * "http://developer.android.com/training/basics/fragments/communicating.html"
	 * >Communicating with Other Fragments</a> for more information.
	 */
	public interface OnFragmentInteractionListener {
		// TODO: Update argument type and name
		public void onFragmentInteraction(Uri uri);
	}

}
