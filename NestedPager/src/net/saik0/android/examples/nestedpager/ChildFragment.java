/**
  Copyright (c) 2012 Joel Pedraza
  Licensed under the Apache License, Version 2.0 (the "License"); you may not
  use this file except in compliance with the License. You may obtain a copy
  of the License at http://www.apache.org/licenses/LICENSE-2.0. Unless required
  by applicable law or agreed to in writing, software distributed under the
  License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS
  OF ANY KIND, either express or implied. See the License for the specific
  language governing permissions and limitations under the License.
  
  @author Joel Pedraza 
*/

package net.saik0.android.examples.nestedpager;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ChildFragment extends Fragment {
	private static final String REAL_POSITION = ":nestedpager:real_position";

	static ChildFragment newInstance(int realPosition) {
		ChildFragment fragment = new ChildFragment();
		Bundle args=new Bundle();
		args.putInt(REAL_POSITION, realPosition);
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_child, container, false);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		int realPosition = getArguments().getInt(REAL_POSITION, -1);
		int blue = realPosition * 15;
		TextView text1 = (TextView) view.findViewById(android.R.id.text1);
		text1.setText(String.format(getActivity().getString(R.string.fragment), realPosition + 1));
		text1.setBackgroundColor(Color.argb(255, 0, 0, blue));
	}

}
