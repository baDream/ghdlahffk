package kr.badream.convenience.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.Rating;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import kr.badream.convenience.Helper.Helper_itemInfo;
import kr.badream.convenience.R;
import kr.badream.convenience.View.View_item_info;
import kr.badream.convenience.View.View_item_list;

/**
 * Created by Administrator on 2016-06-04.
 */
public class Adapter_list_view  extends BaseAdapter {
    // Adapter에 추가된 데이터를 저장하기 위한 ArrayList
    private ArrayList<Helper_itemInfo> listViewItemList = new ArrayList<Helper_itemInfo>() ;

    // ListViewAdapter의 생성자
    public Adapter_list_view() {

    }

    // Adapter에 사용되는 데이터의 개수를 리턴. : 필수 구현
    @Override
    public int getCount() {
        return listViewItemList.size() ;
    }

    // position에 위치한 데이터를 화면에 출력하는데 사용될 View를 리턴. : 필수 구현
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        // "listview_item" Layout을 inflate하여 convertView 참조 획득.
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.adapter_item_list, parent, false);
        }


        // 화면에 표시될 View(Layout이 inflate된)으로부터 위젯에 대한 참조 획득
//        ImageView item_image = (ImageView) convertView.findViewById(R.id.item_image) ;
//        TextView item_name = (TextView) convertView.findViewById(R.id.item_name) ;
//        TextView item_price = (TextView) convertView.findViewById(R.id.item_price) ;
//        TextView like_number = (TextView) convertView.findViewById(R.id.like_number) ;
//        TextView review_number = (TextView) convertView.findViewById(R.id.review_number) ;;


        ImageView item_image = ViewHolderHelper.get(convertView, R.id.item_image);
        ImageView conv_image = ViewHolderHelper.get(convertView, R.id.conv_image);
        TextView item_name = ViewHolderHelper.get(convertView, R.id.item_name);
        TextView item_price = ViewHolderHelper.get(convertView, R.id.item_price);
        TextView like_number = ViewHolderHelper.get(convertView, R.id.like_number);
        TextView review_number = ViewHolderHelper.get(convertView, R.id.review_number);


//        convertView.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                Intent ctgview_intent = new Intent( context , View_item_info.class);
//                context.startActivity(ctgview_intent);
//                Log.e("position","position: " + pos);
//            }
//        });

        // Data Set(listViewItemList)에서 position에 위치한 데이터 참조 획득
        Helper_itemInfo item_list_view = listViewItemList.get(position);

        // 아이템 내 각 위젯에 데이터 반영
        //item_image.setImageDrawable(item_list_view.getMain_image());
        Glide.with(context).load(item_list_view.url).into(item_image);
        item_name.setText(item_list_view.name);
        item_price.setText(item_list_view.price);
        like_number.setText(""+item_list_view.likes);
        review_number.setText(""+item_list_view.reviews);

        switch (item_list_view.storeID){
            case 1:conv_image.setImageResource(R.drawable.cu);break;
            case 2:conv_image.setImageResource(R.drawable.with_me);break;
            case 3:conv_image.setImageResource(R.drawable.gs25);break;
            case 4:conv_image.setImageResource(R.drawable.seven);break;
            case 5:conv_image.setImageResource(R.drawable.ministop);break;
        }

        return convertView;
    }

    // 지정한 위치(position)에 있는 데이터와 관계된 아이템(row)의 ID를 리턴. : 필수 구현
    @Override
    public long getItemId(int position) {
        return position ;
    }

    // 지정한 위치(position)에 있는 데이터 리턴 : 필수 구현
    @Override
    public Object getItem(int position) {
        return listViewItemList.get(position) ;
    }

    public void clear(){
        listViewItemList.clear();
    }

    // 1.이미지, 2.물품이름, 3.가격, 4.좋아요수, 5.리뷰수 6.
    public void addItem(int prodID, String img, String item_name, String item_price,int like_number , int review_number, int conv_image) {
        Helper_itemInfo item = new Helper_itemInfo();

        item.prodID = prodID;
        item.url = (img);
        item.name = (item_name);
        item.price = (item_price);
        item.likes = (like_number);
        item.reviews = (review_number);
        item.storeID = (conv_image);

        listViewItemList.add(item);
    }

    public static class ViewHolderHelper{
        public static <T extends View> T get(View convertView, int id) {

            SparseArray<View> viewHolder = (SparseArray<View>) convertView.getTag();

            if (viewHolder == null) {

                viewHolder = new SparseArray<View>();
                convertView.setTag(viewHolder);
            }

            View childView = viewHolder.get(id);

            if (childView == null) {

                childView = convertView.findViewById(id);
                viewHolder.put(id, childView);
            }
            return (T) childView;
        }
    }


}
