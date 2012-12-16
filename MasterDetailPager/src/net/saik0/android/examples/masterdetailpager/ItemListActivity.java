package net.saik0.android.examples.masterdetailpager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

/**
 * An activity representing a list of Items. This activity has different
 * presentations for handset and tablet-size devices. On handsets, the activity
 * presents a list of items, which when touched, lead to a
 * {@link ItemDetailActivity} representing item details. On tablets, the
 * activity presents the list of items and item details side-by-side using two
 * vertical panes.
 * <p>
 * The activity makes heavy use of fragments. The list of items is a
 * {@link ItemListFragmentTwo} and the item details (if present) is a
 * {@link ItemDetailFragment}.
 * <p>
 * This activity also implements the required {@link ItemListFragmentTwo.Callbacks}
 * interface to listen for item selections.
 */
public class ItemListActivity extends FragmentActivity implements
	ItemListFragmentOne.Callbacks, ItemListFragmentTwo.Callbacks {

	/**
	 * Whether or not the activity is in two-pane mode, i.e. running on a tablet
	 * device.
	 */
	private boolean mTwoPane;
	private ViewPager mViewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_item_pager);

		if (findViewById(R.id.item_detail_container_one) != null) {
			// The detail container view will be present only in the
			// large-screen layouts (res/values-large and
			// res/values-sw600dp). If this view is present, then the
			// activity should be in two-pane mode.
			mTwoPane = true;

			// In two-pane mode, list items should be given the
			// 'activated' state when touched.
			((ItemListFragmentOne) getSupportFragmentManager().findFragmentById(
					R.id.item_list_one)).setActivateOnItemClick(true);
			((ItemListFragmentTwo) getSupportFragmentManager().findFragmentById(
					R.id.item_list_two)).setActivateOnItemClick(true);
		}

		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(new MultiPanePagerAdapter());
	}

	/**
	 * Callback method from {@link ItemListFragmentOne.Callbacks} indicating that
	 * the item with the given ID was selected.
	 */
	@Override
	public void onItemListOneItemSelected(String id) {
		if (mTwoPane) {
			// In two-pane mode, show the detail view in this activity by
			// adding or replacing the detail fragment using a
			// fragment transaction.
			Bundle arguments = new Bundle();
			arguments.putString(ItemDetailFragment.ARG_ITEM_ID, id);
			ItemDetailFragment fragment = new ItemDetailFragment();
			fragment.setArguments(arguments);
			getSupportFragmentManager().beginTransaction()
					.replace(R.id.item_detail_container_one, fragment).commit();

		} else {
			// In single-pane mode, simply start the detail activity
			// for the selected item ID.
			Intent detailIntent = new Intent(this, ItemDetailActivity.class);
			detailIntent.putExtra(ItemDetailFragment.ARG_ITEM_ID, id);
			startActivity(detailIntent);
		}
	}

	/**
	 * Callback method from {@link ItemListFragmentTwo.Callbacks} indicating that
	 * the item with the given ID was selected.
	 */
	@Override
	public void onItemListTwoItemSelected(String id) {
		if (mTwoPane) {
			// In two-pane mode, show the detail view in this activity by
			// adding or replacing the detail fragment using a
			// fragment transaction.
			Bundle arguments = new Bundle();
			arguments.putString(ItemDetailFragment.ARG_ITEM_ID, id);
			ItemDetailFragment fragment = new ItemDetailFragment();
			fragment.setArguments(arguments);
			getSupportFragmentManager().beginTransaction()
					.replace(R.id.item_detail_container_two, fragment).commit();

		} else {
			// In single-pane mode, simply start the detail activity
			// for the selected item ID.
			Intent detailIntent = new Intent(this, ItemDetailActivity.class);
			detailIntent.putExtra(ItemDetailFragment.ARG_ITEM_ID, id);
			startActivity(detailIntent);
		}
	}

	private class MultiPanePagerAdapter extends PagerAdapter {
		private static final int PAGE_ONE_ID = 0;
		private static final int PAGE_TWO_ID = 1;
		private static final int COUNT = 2;

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			switch (position) {
			case PAGE_ONE_ID:
				return findViewById(R.id.page_item_list_one);
			case PAGE_TWO_ID:
				return findViewById(R.id.page_item_list_two);
			default:
				throw new IllegalArgumentException("Unhandled page for position " + position);
			}
		}

		@Override
		public CharSequence getPageTitle(int position) {
			switch (position) {
			case PAGE_ONE_ID:
				return getString(R.string.page_one_title);
			case PAGE_TWO_ID:
				return getString(R.string.page_two_title);
			default:
				throw new IllegalArgumentException("Unhandled page for position " + position);
			}
		}

		@Override
		public int getCount() {
			return COUNT;
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			return(view == object);
		}
	}
}
