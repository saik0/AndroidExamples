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
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.ViewGroup;


public class NestedPagerActivity extends FragmentActivity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nested_pager);

		ViewPager pager = (ViewPager) findViewById(R.id.pager);
		pager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()){
			private static final int PARENT_COUNT = 4;
			private static final int CHILD_COUNT = 3;

			@Override
			public Fragment getItem(int position) {
				return ParentFragment.newInstance(position, CHILD_COUNT);
			}

			@Override
			public String getPageTitle(int position) {
				return String.format(getString(R.string.page), position + 1);
			}

			@Override
			public int getCount() {
				return PARENT_COUNT;
			}

			@Override
			public void destroyItem(ViewGroup container, int position, Object object) {
				super.destroyItem(container, position, object);
				((Fragment) object).getFragmentManager()
						.beginTransaction()
						.remove(((Fragment) object))
						.commit();
			}
		});
	}
}
