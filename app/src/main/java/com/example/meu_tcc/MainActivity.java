package com.example.meu_tcc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.Toolbar;

import com.example.meu_tcc.adapter.BannerMoviesPagerAdapter;
import com.example.meu_tcc.adapter.MainRecyclerAdapter;
import com.example.meu_tcc.models.AllCategory;
import com.example.meu_tcc.models.BannerMovies;
import com.example.meu_tcc.models.CategoryItem;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    BannerMoviesPagerAdapter bannerMoviesPagerAdapter;
    TabLayout tabIndicator, categoryTab;
    ViewPager bannerMoviesViewPager;
    List<BannerMovies> homeBannerList;
    List<BannerMovies> tvShowBannerList;
    List<BannerMovies> movieBannerList;
    List<BannerMovies> kidsBannerList;
    Timer sliderTimer;
    NestedScrollView nestedScrollView;
    AppBarLayout appBarLayout;

    MainRecyclerAdapter mainRecyclerAdapter;
    RecyclerView mainRecycler;
    List<AllCategory> allCategoryList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabIndicator = findViewById(R.id.tab_indicator);
        categoryTab = findViewById(R.id.tab_indicator);
        nestedScrollView = findViewById(R.id.nested_scroll);
        appBarLayout = findViewById(R.id.appbar);


        //Colocar os links das imagens a seguir, imagens Carrosel//
        homeBannerList = new ArrayList<>();
        homeBannerList.add(new BannerMovies(1,"","",""));
        homeBannerList.add(new BannerMovies(1,"","",""));
        homeBannerList.add(new BannerMovies(1,"","",""));
        homeBannerList.add(new BannerMovies(1,"","",""));
        homeBannerList.add(new BannerMovies(1,"","",""));
 //links de imagens relacionadas a Filmes novos
        tvShowBannerList = new ArrayList<>();
        tvShowBannerList.add(new BannerMovies(1,"","",""));
        tvShowBannerList.add(new BannerMovies(1,"","",""));
        tvShowBannerList.add(new BannerMovies(1,"","",""));
 //Séries
        movieBannerList = new ArrayList<>();
        movieBannerList.add(new BannerMovies(1,"","",""));
        movieBannerList.add(new BannerMovies(1,"","",""));
        movieBannerList.add(new BannerMovies(1,"","",""));
//animes
        kidsBannerList = new ArrayList<>();
        kidsBannerList.add(new BannerMovies(1,"","",""));
        kidsBannerList.add(new BannerMovies(1,"","",""));
        kidsBannerList.add(new BannerMovies(1,"","",""));
        kidsBannerList.add(new BannerMovies(1,"","",""));


        setBannerMoviesPagerAdapter(homeBannerList);

        categoryTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 1:
                        setScrollDefaultState();
                        setBannerMoviesPagerAdapter(tvShowBannerList);
                        return;
                    case 2:
                        setScrollDefaultState();
                        setBannerMoviesPagerAdapter(movieBannerList);

                        return;
                    case 3:
                        setScrollDefaultState();
                        setBannerMoviesPagerAdapter(kidsBannerList);
                        return;

                    default:
                        setScrollDefaultState();
                        setBannerMoviesPagerAdapter(homeBannerList);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        //colocar imagens conforme mostrado

        //lançamentos
        List<CategoryItem> homeCatListItem1 = new ArrayList<>();
        homeCatListItem1.add(new CategoryItem(1,"","",""));
        homeCatListItem1.add(new CategoryItem(1,"","",""));
        homeCatListItem1.add(new CategoryItem(1,"","",""));
        homeCatListItem1.add(new CategoryItem(1,"","",""));
        homeCatListItem1.add(new CategoryItem(1,"","",""));
       //Filmes
        List<CategoryItem> homeCatListItem2 = new ArrayList<>();
        homeCatListItem1.add(new CategoryItem(1,"","",""));
        homeCatListItem1.add(new CategoryItem(1,"","",""));
        homeCatListItem1.add(new CategoryItem(1,"","",""));
        homeCatListItem1.add(new CategoryItem(1,"","",""));
        homeCatListItem1.add(new CategoryItem(1,"","",""));
//Séries
        List<CategoryItem> homeCatListItem3 = new ArrayList<>();
        homeCatListItem1.add(new CategoryItem(1,"","",""));
        homeCatListItem1.add(new CategoryItem(1,"","",""));
        homeCatListItem1.add(new CategoryItem(1,"","",""));
        homeCatListItem1.add(new CategoryItem(1,"","",""));
        homeCatListItem1.add(new CategoryItem(1,"","",""));
//Animes
        List<CategoryItem> homeCatListItem4 = new ArrayList<>();
        homeCatListItem1.add(new CategoryItem(1,"","",""));
        homeCatListItem1.add(new CategoryItem(1,"","",""));
        homeCatListItem1.add(new CategoryItem(1,"","",""));
        homeCatListItem1.add(new CategoryItem(1,"","",""));
        homeCatListItem1.add(new CategoryItem(1,"","",""));



        allCategoryList = new ArrayList<>();
        allCategoryList.add(new AllCategory(1,"Lançamentos", homeCatListItem1));
        allCategoryList.add(new AllCategory(1,"Filmes", homeCatListItem2));
        allCategoryList.add(new AllCategory(1,"Séries", homeCatListItem3));
        allCategoryList.add(new AllCategory(1,"Animes", homeCatListItem4));


        setMainRecycler(allCategoryList);



    }

    private void setBannerMoviesPagerAdapter(List<BannerMovies> bannerMoviesList){

        bannerMoviesViewPager = findViewById(R.id.banner_viewPager);
        bannerMoviesPagerAdapter = new BannerMoviesPagerAdapter(this,bannerMoviesList);
        bannerMoviesViewPager.setAdapter(bannerMoviesPagerAdapter);
        tabIndicator.setupWithViewPager(bannerMoviesViewPager);

        Timer sliderTimer = new Timer();
        sliderTimer.scheduleAtFixedRate(new AutoSlider(), 4000,6000);
        tabIndicator.setupWithViewPager(bannerMoviesViewPager,true);

    }

    class AutoSlider extends TimerTask{

        @Override
        public void run() {
            MainActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                   if (bannerMoviesViewPager.getCurrentItem() < homeBannerList.size() -1){

                       bannerMoviesViewPager.setCurrentItem(bannerMoviesViewPager.getCurrentItem() +1);
                    }
                    else{
                        bannerMoviesViewPager.setCurrentItem(0);
                    }
                }
            });

        }
    }

    public void  setMainRecycler(List<AllCategory> allCategoryList){

        mainRecycler = findViewById(R.id.main_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL, false);
        mainRecycler.setLayoutManager(layoutManager);
        mainRecyclerAdapter =new MainRecyclerAdapter(this,allCategoryList);
        mainRecycler.setAdapter(mainRecyclerAdapter);

    }

    private void  setScrollDefaultState(){

        nestedScrollView.fullScroll(View.FOCUS_UP);
        nestedScrollView.scrollTo(0,0);
        appBarLayout.setExpanded(true);

    }


}