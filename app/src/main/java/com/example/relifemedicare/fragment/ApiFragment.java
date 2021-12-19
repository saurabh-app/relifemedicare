package com.example.relifemedicare.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.relifemedicare.R;
import com.example.relifemedicare.adapter.RelifemedicareApiAdapter;
import com.example.relifemedicare.model.Hit;
import com.example.relifemedicare.model.MasterResponceMadel;
import com.example.relifemedicare.network.ApiInterface;
import com.example.relifemedicare.network.RetrofitInstance;

import java.util.List;

import static android.content.ContentValues.TAG;

public class ApiFragment extends Fragment {

    private String q="yellow+flowers";
    private String image_type="photo";
    private String  pretty="true";
    private RecyclerView rv_api;
    private ApiInterface apiInterface;
    private MasterResponceMadel masterResponseModel;
    private RelifemedicareApiAdapter allMatchListAdapter;

    public ApiFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_api,null);
        rv_api=view.findViewById(R.id.rv_api);
        rv_api.setLayoutManager(new LinearLayoutManager(getContext()));
        apiInterface = RetrofitInstance.getClient().create(ApiInterface.class);
        getAllDataList();
        return view;
    }

    private void getAllDataList() {
        ProgressDialog progressBar = new ProgressDialog(getContext());
        progressBar.setCancelable(true);//you can cancel it by pressing back button
        progressBar.setMessage("downloading ...");
        progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressBar.setProgress(2);//initially progress is 0
        progressBar.setMax(150);//sets the maximum value 100
        progressBar.show();//displays the progress bar
        Call<MasterResponceMadel> call = apiInterface.getAlllists(q,image_type,pretty);

        call.enqueue(new Callback<MasterResponceMadel>() {
            @Override
            public void onResponse(Call<MasterResponceMadel> call, Response<MasterResponceMadel> response) {
                if(response.isSuccessful()){
                    masterResponseModel=response.body();
                    Log.d(TAG,"Onresponce"+response.body());
                    List<Hit> hit = (List<Hit>) masterResponseModel.getHits();

                    allMatchListAdapter =new RelifemedicareApiAdapter(getContext() ,hit);
                    rv_api.setAdapter(allMatchListAdapter);
                    allMatchListAdapter.notifyDataSetChanged();
                    progressBar.dismiss();
                }
                else {
                    Toast.makeText(getContext(), "Error! Please try again!"+response.toString(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<MasterResponceMadel> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                progressBar.dismiss();

            }
        });
    }

}