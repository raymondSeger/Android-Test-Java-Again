package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Parcelable;

import com.google.firebase.analytics.FirebaseAnalytics;

public class FirebaseAnalyticsActivity extends AppCompatActivity {

    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_analytics);

        // Obtain the FirebaseAnalytics instance.
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        // set user ID
        mFirebaseAnalytics.setUserId("1");

        // set user property
        mFirebaseAnalytics.setUserProperty("hobby", "travelling");
        mFirebaseAnalytics.setUserProperty("name", "Raymond Seger");

        // manually track scene
        Bundle bundle1 = new Bundle();
        bundle1.putString(FirebaseAnalytics.Param.SCREEN_NAME, "FirebaseAnalyticsActivity page");
        bundle1.putString(FirebaseAnalytics.Param.SCREEN_CLASS, "FirebaseAnalyticsActivity");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW, bundle1);

        // events in https://support.google.com/analytics/answer/9267735?visit_id=637466321031765200-760512818&rd=1

        // The following code logs a SELECT_CONTENT event when a user clicks on a specific element in your app.
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "1");
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "Item Name");
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "image");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);

        // user register 1
        Bundle bundle2 = new Bundle();
        bundle2.putString(FirebaseAnalytics.Param.METHOD, "From Sign up Page");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SIGN_UP, bundle2);

        // user register 2
        Bundle bundle3 = new Bundle();
        bundle3.putString(FirebaseAnalytics.Param.METHOD, "From Sign up Page Version 2");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SIGN_UP, bundle3);

        // view product detail
        Bundle itemJeggings = new Bundle();
        itemJeggings.putString(FirebaseAnalytics.Param.ITEM_ID, "SKU_123");
        itemJeggings.putString(FirebaseAnalytics.Param.ITEM_NAME, "jeggings");
        itemJeggings.putString(FirebaseAnalytics.Param.ITEM_CATEGORY, "pants");
        itemJeggings.putString(FirebaseAnalytics.Param.ITEM_VARIANT, "black");
        itemJeggings.putString(FirebaseAnalytics.Param.ITEM_BRAND, "Google");
        itemJeggings.putDouble(FirebaseAnalytics.Param.PRICE, 9.99);

        Bundle itemBoots = new Bundle();
        itemBoots.putString(FirebaseAnalytics.Param.ITEM_ID, "SKU_456");
        itemBoots.putString(FirebaseAnalytics.Param.ITEM_NAME, "boots");
        itemBoots.putString(FirebaseAnalytics.Param.ITEM_CATEGORY, "shoes");
        itemBoots.putString(FirebaseAnalytics.Param.ITEM_VARIANT, "brown");
        itemBoots.putString(FirebaseAnalytics.Param.ITEM_BRAND, "Google");
        itemBoots.putDouble(FirebaseAnalytics.Param.PRICE, 24.99);

        Bundle itemSocks = new Bundle();
        itemSocks.putString(FirebaseAnalytics.Param.ITEM_ID, "SKU_789");
        itemSocks.putString(FirebaseAnalytics.Param.ITEM_NAME, "ankle_socks");
        itemSocks.putString(FirebaseAnalytics.Param.ITEM_CATEGORY, "socks");
        itemSocks.putString(FirebaseAnalytics.Param.ITEM_VARIANT, "red");
        itemSocks.putString(FirebaseAnalytics.Param.ITEM_BRAND, "Google");
        itemSocks.putDouble(FirebaseAnalytics.Param.PRICE, 5.99);

        Bundle itemJeggingsWithIndex = new Bundle(itemJeggings);
        itemJeggingsWithIndex.putLong(FirebaseAnalytics.Param.INDEX, 1);

        Bundle itemBootsWithIndex = new Bundle(itemBoots);
        itemBootsWithIndex.putLong(FirebaseAnalytics.Param.INDEX, 2);

        Bundle itemSocksWithIndex = new Bundle(itemSocks);
        itemSocksWithIndex.putLong(FirebaseAnalytics.Param.INDEX, 3);

        Bundle viewItemListParams = new Bundle();
        viewItemListParams.putString(FirebaseAnalytics.Param.ITEM_LIST_ID, "L001");
        viewItemListParams.putString(FirebaseAnalytics.Param.ITEM_LIST_NAME, "Related products");
        viewItemListParams.putParcelableArray(FirebaseAnalytics.Param.ITEMS, new Parcelable[]{ itemJeggingsWithIndex, itemBootsWithIndex, itemSocksWithIndex });
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.VIEW_ITEM_LIST, viewItemListParams);

        Bundle viewItemParams = new Bundle();
        viewItemParams.putString(FirebaseAnalytics.Param.CURRENCY, "USD");
        viewItemParams.putDouble(FirebaseAnalytics.Param.VALUE, 9.99);
        viewItemParams.putParcelableArray(FirebaseAnalytics.Param.ITEMS, new Parcelable[] { itemJeggings });
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.VIEW_ITEM, viewItemParams);

        // they put to shopping bag
        Bundle selectItemParams = new Bundle();
        selectItemParams.putString(FirebaseAnalytics.Param.ITEM_LIST_ID, "L001");
        selectItemParams.putString(FirebaseAnalytics.Param.ITEM_LIST_NAME, "Related products");
        selectItemParams.putParcelableArray(FirebaseAnalytics.Param.ITEMS, new Parcelable[]{ itemJeggings });
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_ITEM, selectItemParams);

        // add to wish list
        Bundle itemJeggingsWishlist = new Bundle(itemJeggings);
        itemJeggingsWishlist.putLong(FirebaseAnalytics.Param.QUANTITY, 2);
        Bundle addToWishlistParams = new Bundle();
        addToWishlistParams.putString(FirebaseAnalytics.Param.CURRENCY, "USD");
        addToWishlistParams.putDouble(FirebaseAnalytics.Param.VALUE, 2 * 9.99);
        addToWishlistParams.putParcelableArray(FirebaseAnalytics.Param.ITEMS, new Parcelable[]{ itemJeggingsWishlist });
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.ADD_TO_WISHLIST, addToWishlistParams);

        // view shopping bag
        Bundle itemJeggingsCart = new Bundle(itemJeggings);
        itemJeggingsCart.putLong(FirebaseAnalytics.Param.QUANTITY, 2);
        Bundle itemBootsCart = new Bundle(itemBoots);
        itemBootsCart.putLong(FirebaseAnalytics.Param.QUANTITY, 1);
        Bundle viewCartParams = new Bundle();
        viewCartParams.putString(FirebaseAnalytics.Param.CURRENCY, "USD");
        viewCartParams.putDouble(FirebaseAnalytics.Param.VALUE, (2 * 9.99) + (1 * 24.99));
        viewCartParams.putParcelableArray(FirebaseAnalytics.Param.ITEMS, new Parcelable[]{ itemJeggingsCart, itemBootsCart });
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.VIEW_CART, viewCartParams);

        // remove item from shopping bag
        Bundle removeCartParams = new Bundle();
        removeCartParams.putString(FirebaseAnalytics.Param.CURRENCY, "USD");
        removeCartParams.putDouble(FirebaseAnalytics.Param.VALUE, (1 * 24.99));
        removeCartParams.putParcelableArray(FirebaseAnalytics.Param.ITEMS, new Parcelable[]{ itemBootsCart });
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.REMOVE_FROM_CART, removeCartParams);

        // begin checkout
        Bundle beginCheckoutParams = new Bundle();
        beginCheckoutParams.putString(FirebaseAnalytics.Param.CURRENCY, "USD");
        beginCheckoutParams.putDouble(FirebaseAnalytics.Param.VALUE, 14.98);
        beginCheckoutParams.putString(FirebaseAnalytics.Param.COUPON, "SUMMER_FUN");
        beginCheckoutParams.putParcelableArray(FirebaseAnalytics.Param.ITEMS, new Parcelable[]{ itemJeggingsCart });
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.BEGIN_CHECKOUT, beginCheckoutParams);

        // add shipping info
        Bundle addShippingParams = new Bundle();
        addShippingParams.putString(FirebaseAnalytics.Param.CURRENCY, "USD");
        addShippingParams.putDouble(FirebaseAnalytics.Param.VALUE, 14.98);
        addShippingParams.putString(FirebaseAnalytics.Param.COUPON, "SUMMER_FUN");
        addShippingParams.putString(FirebaseAnalytics.Param.SHIPPING_TIER, "Ground");
        addShippingParams.putParcelableArray(FirebaseAnalytics.Param.ITEMS, new Parcelable[]{ itemJeggingsCart });
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.ADD_SHIPPING_INFO, addShippingParams);

        // add payment info
        Bundle addPaymentParams = new Bundle();
        addPaymentParams.putString(FirebaseAnalytics.Param.CURRENCY, "USD");
        addPaymentParams.putDouble(FirebaseAnalytics.Param.VALUE, 14.98);
        addPaymentParams.putString(FirebaseAnalytics.Param.COUPON, "SUMMER_FUN");
        addPaymentParams.putString(FirebaseAnalytics.Param.PAYMENT_TYPE, "Visa");
        addPaymentParams.putParcelableArray(FirebaseAnalytics.Param.ITEMS, new Parcelable[]{ itemJeggingsCart });
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.ADD_PAYMENT_INFO, addPaymentParams);

        // buy / purchase / already sent the money
        Bundle purchaseParams = new Bundle();
        purchaseParams.putString(FirebaseAnalytics.Param.TRANSACTION_ID, "T12345");
        purchaseParams.putString(FirebaseAnalytics.Param.AFFILIATION, "Google Store");
        purchaseParams.putString(FirebaseAnalytics.Param.CURRENCY, "USD");
        purchaseParams.putDouble(FirebaseAnalytics.Param.VALUE, 14.98);
        purchaseParams.putDouble(FirebaseAnalytics.Param.TAX, 2.58);
        purchaseParams.putDouble(FirebaseAnalytics.Param.SHIPPING, 5.34);
        purchaseParams.putString(FirebaseAnalytics.Param.COUPON, "SUMMER_FUN");
        purchaseParams.putParcelableArray(FirebaseAnalytics.Param.ITEMS, new Parcelable[]{ itemJeggingsCart });
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.PURCHASE, purchaseParams);

        // promotion banner clicked
        Bundle promoParams = new Bundle();
        promoParams.putString(FirebaseAnalytics.Param.PROMOTION_ID, "SUMMER_FUN");
        promoParams.putString(FirebaseAnalytics.Param.PROMOTION_NAME, "Summer Sale");
        promoParams.putString(FirebaseAnalytics.Param.CREATIVE_NAME, "summer2020_promo.jpg");
        promoParams.putString(FirebaseAnalytics.Param.CREATIVE_SLOT, "featured_app_1");
        promoParams.putString(FirebaseAnalytics.Param.LOCATION_ID, "HERO_BANNER");
        promoParams.putParcelableArray(FirebaseAnalytics.Param.ITEMS, new Parcelable[]{ itemJeggings });
        // Promotion displayed
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.VIEW_PROMOTION, promoParams);
        // Promotion selected
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_PROMOTION, promoParams);

    }
}