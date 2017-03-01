package bldg5.jj.itp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import bldg5.jj.itp.adapters.HListDataAdapter;
import bldg5.jj.itp.common.BaseNavDrawer;
import bldg5.jj.itp.models.Item;
import rx.Observer;
import rx.subjects.PublishSubject;

public class SingleItem extends BaseNavDrawer {
    private RecyclerView mRecyclerView;
    private HListDataAdapter adapter;
    private PublishSubject<Item> mPubItemDetails;
    private TextView mTextTitle;
    private TextView mTextRepValue;
    private TextView mTextViewed;
    private TextView mTextCondition;
    private TextView mTextDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_single_item);

        // now run the base's onCreate
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        Item auto_item = (Item) intent.getExtras().getSerializable("item");

        mTextTitle = (TextView) findViewById(R.id.txtTitle);
        mTextRepValue = (TextView) findViewById(R.id.txtRepValue);
        mTextViewed = (TextView) findViewById(R.id.txtViewed);
        mTextCondition = (TextView) findViewById(R.id.txtCondition);
        mTextDesc = (TextView) findViewById(R.id.txtDesc);


        createPublisher();
        mPubItemDetails.onNext(auto_item);

        // bind the button
        Button btnWant = (Button) findViewById(R.id.btnWant);
        btnWant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast = Toast.makeText(getApplicationContext(), "You must be logged in to do that.", Toast.LENGTH_LONG);
                toast.show();
            }
        });

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_list);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        adapter = new HListDataAdapter(getApplicationContext(), auto_item.getAllPhotos());
        mRecyclerView.setAdapter(adapter);
    }

    private void createPublisher() {
        mPubItemDetails = PublishSubject.create();

        mPubItemDetails.subscribe(new Observer<Item>() {
            @Override
            public void onCompleted() { }

            @Override
            public void onError(Throwable e) { }

            @Override
            public void onNext(Item nextItem) {
                mTextTitle.setText(String.valueOf(nextItem.getTitle()));
                mTextRepValue.setText(String.valueOf(nextItem.getReplacementValue()));
                mTextViewed.setText(String.valueOf(nextItem.getViewed()));
                mTextCondition.setText(String.valueOf(nextItem.getCondition()));
                mTextDesc.setText(String.valueOf(nextItem.getDescription()));
            }
        });
    }
}
