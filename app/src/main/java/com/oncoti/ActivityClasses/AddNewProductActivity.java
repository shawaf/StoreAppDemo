package com.oncoti.ActivityClasses;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.oncoti.Fragments.AddNewProductFragment;
import com.oncoti.Fragments.ProductListFragment;
import com.oncoti.Models.ProductModel;
import com.oncoti.R;

import java.util.ArrayList;
import java.util.List;

public class AddNewProductActivity extends AppCompatActivity implements View.OnClickListener {

    private FragmentManager fm;
    private FragmentTransaction ft;
    private Toolbar mToolbar;
    private TextView addprodTitle;
    private Button sumbitBtn;
    private ImageButton backBtn;
    private List<ProductModel> prodList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_product);
        prodList = new ArrayList<>();

        initToolbar();
        initFragment();
    }

    public void setTitle(String title, String submitText) {
        addprodTitle.setText(title);
        sumbitBtn.setText(submitText);

    }

    private void initToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.add_product_toolbar);
        addprodTitle = (TextView) mToolbar.findViewById(R.id.addproduct_toolbar_addproducts_title);
        sumbitBtn = (Button) mToolbar.findViewById(R.id.addproduct_toolbar_submit);
        backBtn = (ImageButton) mToolbar.findViewById(R.id.addproduct_toolbar_backbtn);
        setSupportActionBar(mToolbar);

        backBtn.setOnClickListener(this);
        sumbitBtn.setOnClickListener(this);

    }

    private void initFragment() {
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();

        ProductListFragment productListFragment = new ProductListFragment();
        ft.replace(R.id.add_product__fragholder, productListFragment, "prod_list");
        ft.commit();

    }

    public void OpenNewProdFragment() {
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        ft.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);

        AddNewProductFragment addNewProductFragment = new AddNewProductFragment();
        ft.replace(R.id.add_product__fragholder, addNewProductFragment, "new_prod");
        ft.addToBackStack(null);
        ft.commit();
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.addproduct_toolbar_submit:
                AddNewProductFragment addNewProductFragment = (AddNewProductFragment) getSupportFragmentManager().findFragmentByTag("new_prod");
                ProductListFragment productListFragment = (ProductListFragment) getSupportFragmentManager().findFragmentByTag("prod_list");
                if (addNewProductFragment != null && addNewProductFragment.isVisible()) {
                    addNewProductFragment.submitProduct();
                } else if (productListFragment != null && productListFragment.isVisible()) {
                    productListFragment.uploadProducts();
                }
                break;
        }
    }

    public int addToProducts(ProductModel productModel) {
        prodList.add(productModel);

        return prodList.size();
    }

    public List<ProductModel> getProducts() {
        if (prodList.size() != 0) {
            return prodList;
        } else {
            return null;
        }
    }

    public void deleteProduct(int no) {
        prodList.remove(no);
    }

}
