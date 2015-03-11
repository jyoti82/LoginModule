package com.jyoti.loginmodule;
import java.util.ArrayList;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


public class SlidingMenuList extends ListFragment {
	private ArrayList<String> list_items;
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View V = inflater.inflate(R.layout.sliding_menu_list, container, false);
		ImageView iv = (ImageView) V.findViewById(R.id.imageView1);
		/*try {
			Drawable msoImageResource = SupportedMSOHelper.getInstance().getImageByMSOId(UserProfileService.getAuthenticatedUserInfo().getMsoInfo().getMsoIdentifier(), "providerlogotop");
			iv.setImageDrawable(msoImageResource);
		} catch (Exception e) {

		}
		ImageView listHeader = (ImageView) V.findViewById(R.id.divider_header_view);
		listHeader.setImageDrawable(getResources().getDrawable(R.color.inactive));
		ImageView listFooter = (ImageView)V.findViewById(R.id.divider_footer_view);
		listFooter.setImageDrawable(getResources().getDrawable(R.color.inactive));
		*/
		return V;
	}
}
