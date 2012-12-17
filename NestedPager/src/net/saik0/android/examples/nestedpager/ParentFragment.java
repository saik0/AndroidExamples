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

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ParentFragment extends Fragment {
	private static final String KEY_POSITION = ":nestedpager:key_position";
	private static final String CHILD_COUNT = ":nestedpager:child_count";

	static ParentFragment newInstance(int keyPosition, int childCount) {
		ParentFragment fragment = new ParentFragment();
		Bundle args=new Bundle();
		args.putInt(KEY_POSITION, keyPosition);
		args.putInt(CHILD_COUNT, childCount);
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_parent, container, false);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		int keyPosition = getArguments().getInt(KEY_POSITION, -1);
		int childCount = getArguments().getInt(CHILD_COUNT, -1);

		for (int i = 0; i < childCount; i++) {
			getChildFragmentManager()
					.beginTransaction()
					.add(R.id.fragment_container, ChildFragment.newInstance(keyPosition * childCount + i))
					.commit();
		}
	}

}
