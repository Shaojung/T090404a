package net.cloud95.android.lession.ebook1;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v13.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;

public class BookActivity extends FragmentActivity {
    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book);

        //創建 ViewPager 與 PagerAdapter
        mPager = (ViewPager) findViewById(R.id.pager);
        mPagerAdapter = new ScreenSlidePagerAdapter(getFragmentManager());
        //連接  ViewPager 與 PagerAdapter
        mPager.setAdapter(mPagerAdapter);

        mPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //重刷 Menu 選單
                invalidateOptionsMenu();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        //載入 menu 描述檔
        getMenuInflater().inflate(R.menu.menu, menu);
        //假如不是第一頁，上一頁 按鈕可按

        menu.findItem(R.id.action_previous).setEnabled(mPager.getCurrentItem() > 0);
        //決定 下一頁 按鈕顯示的文字
        MenuItem item = menu
                        .add(Menu.NONE, R.id.action_next, Menu.NONE,
                (mPager.getCurrentItem() == mPagerAdapter.getCount() - 1)
                        ? R.string.action_finish
                        : R.string.action_next)
                //決定 下一頁 按鈕顯示的圖示
                        .setIcon(
                 (mPager.getCurrentItem() == mPagerAdapter.getCount() - 1)
                                        ? android.R.drawable.ic_menu_close_clear_cancel
                                        :android.R.drawable.ic_media_next);
        item.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM | MenuItem.SHOW_AS_ACTION_WITH_TEXT);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            //切換到上一頁
            case R.id.action_previous:
                mPager.setCurrentItem(mPager.getCurrentItem() - 1);
                return true;
            //切換到下一頁
            case R.id.action_next:
                mPager.setCurrentItem(mPager.getCurrentItem() + 1);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
  //自訂的類別
  //繼承自 FragmentStatePagerAdapter 適用於頁數多者；使用的記憶體固定為三頁
  //繼承自 FragmentPagerAdapter 適用於頁數少者，切換速度較快；使用的記憶體包含所有頁數
    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
      public ScreenSlidePagerAdapter(FragmentManager fm) {
          super(fm);
      }

      @Override
      public Fragment getItem(int position) {
          //取得第 position 畫面，呼叫 Fragment 的自訂函式 create

          return PageFragment.create(position);
      }

      @Override
      public int getCount() {
          //取得畫面數量
          return PageFragment.contents.length;
      }

    }
}
