package kr.badream.convenience.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import kr.badream.convenience.Helper.Helper_reviewData;
import kr.badream.convenience.R;
import kr.badream.convenience.View.View_item_info;

/**
 * Created by Administrator on 2016-06-10.
 */
public class Adapter_review_list_view  extends BaseAdapter {
    // Adapter에 추가된 데이터를 저장하기 위한 ArrayList
    private ArrayList<Helper_reviewData> reviewViewItemList = new ArrayList<Helper_reviewData>() ;

    // ListViewAdapter의 생성자
    public Adapter_review_list_view() {

    }

    // Adapter에 사용되는 데이터의 개수를 리턴. : 필수 구현
    @Override
    public int getCount() {
        return reviewViewItemList.size() ;
    }

    // position에 위치한 데이터를 화면에 출력하는데 사용될 View를 리턴. : 필수 구현
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        // "listview_item" Layout을 inflate하여 convertView 참조 획득.
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.adapter_review_list, parent, false);
        }


        // 화면에 표시될 View(Layout이 inflate된)으로부터 위젯에 대한 참조 획득

//        ImageView item_image = (ImageView) convertView.findViewById(R.id.item_image) ;
//        TextView user_name = (TextView) convertView.findViewById(R.id.user_name) ;
//        TextView item_price = (TextView) convertView.findViewById(R.id.item_price) ;
//        TextView like_number = (TextView) convertView.findViewById(R.id.like_number);
//        TextView review_main = (TextView) convertView.findViewById(R.id.review_main);

        ImageView item_image = ViewHolderHelper.get(convertView, R.id.item_image);
        TextView user_name = ViewHolderHelper.get(convertView, R.id.user_name);
        TextView item_price = ViewHolderHelper.get(convertView, R.id.item_price);
        TextView like_number = ViewHolderHelper.get(convertView, R.id.like_number);
        TextView review_main = ViewHolderHelper.get(convertView, R.id.review_main);

        convertView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
//                Intent ctgview_intent = new Intent( context , View_item_info.class);
//                context.startActivity(ctgview_intent);
//                Log.e("position","position: " + pos);
            }
        });

        // Data Set(listViewItemList)에서 position에 위치한 데이터 참조 획득
        Helper_reviewData item_review_view = reviewViewItemList.get(position);

        // 아이템 내 각 위젯에 데이터 반영
        //item_image.setImageDrawable(item_review_view.getMain_image());
        Glide.with(context).load(item_review_view.url).into(item_image);
        user_name.setText(item_review_view.userName);
        item_price.setText(item_review_view.price);
        like_number.setText(""+item_review_view.likes);
        review_main.setText(item_review_view.contents);

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
        return reviewViewItemList.get(position) ;
    }

    // 1.이미지, 2.유저이름, 3.총 가격, 4.좋아요수, 5.리뷰내용
    public void addItem(String img, String user_name, String item_price, int like_number, String review_main) {
        Helper_reviewData item = new Helper_reviewData();

        item.url = img;
        item.userName = user_name;
        item.price = item_price;
        item.likes = (like_number);
        item.contents = (review_main);

        reviewViewItemList.add(item);
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
