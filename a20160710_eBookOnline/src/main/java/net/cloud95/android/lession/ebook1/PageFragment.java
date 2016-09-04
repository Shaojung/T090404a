package net.cloud95.android.lession.ebook1;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class PageFragment extends Fragment {
	public static final int[] contents={
			R.string.cotent001, R.string.cotent002, R.string.cotent003, R.string.cotent004, R.string.cotent005,
			R.string.cotent006, R.string.cotent007, R.string.cotent008, R.string.cotent009,
			R.string.cotent010, R.string.cotent011, R.string.cotent012, R.string.cotent013, R.string.cotent014,
			R.string.cotent015, R.string.cotent016, R.string.cotent017, R.string.cotent018, R.string.cotent019,
			R.string.cotent020
	};
	public static final int[] minions={
		R.drawable.list000, R.drawable.list001, R.drawable.list002, R.drawable.list003,
		R.drawable.list004, R.drawable.list005, R.drawable.list006, R.drawable.list007,
		R.drawable.list008, R.drawable.list009
};
	//傳遞參數的參數名
    public static final String ARG_PAGE = "page";
    //頁碼
    private int mPageNumber;
    //提供給外部建立頁面用函式
    public static PageFragment create(int pageNumber) {
        //實作頁面
        PageFragment fragment = new PageFragment();
        //準備傳遞 頁碼 參數
        Bundle args = new Bundle();

        args.putInt(ARG_PAGE, pageNumber);
        //將 頁碼 參數加附到 Fragment
        fragment.setArguments(args);
        return fragment;
    }

    public PageFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //取得傳遞參數
        mPageNumber = getArguments().getInt(ARG_PAGE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        //準備 Fragment 的畫面
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.page, container, false);
        //從 res/value/strings.xml 中取得字串，並傳入參數
        ((TextView) rootView.findViewById(android.R.id.text1)).setText(getString(R.string.title_template_step,mPageNumber+1));
        ((TextView) rootView.findViewById(android.R.id.text2)).setText(getString(contents[mPageNumber]));
        ((ImageView) rootView.findViewById(R.id.minion)).setImageResource(minions[mPageNumber%minions.length]);

        return rootView;

        //從 res/drawable中取得圖示，並放入頁首
    }
    public int getPageNumber() {
    	//回傳目前頁碼
        return mPageNumber;
    }
}
