package pamir.com.advancedimageview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

/**
 * Created by Pamir on 20.09.2016.
 */

public class AdvancedImageView extends ImageView {

    private String mUrl;
    private Type mType;
    private Source mSource;

    public AdvancedImageView(Context context) {
        super(context);
    }

    public AdvancedImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.AdvancedImageView, 0, 0);

        if( a.hasValue(R.styleable.AdvancedImageView_url)){
            mUrl = a.getString(R.styleable.AdvancedImageView_url);
            loadFromUrl();
        }

        if( a.hasValue(R.styleable.AdvancedImageView_type)){
            int typeId = a.getInt(R.styleable.AdvancedImageView_type, 0);
            mType = Type.getValue(typeId);
        }

        if( a.hasValue(R.styleable.AdvancedImageView_library)){
            int sourceId = a.getInt(R.styleable.AdvancedImageView_library, 0);
            mSource = Source.getValue(sourceId);
        }

        a.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getMeasuredWidth();
        switch (mType){
            case PORTRAIT:
                setMeasuredDimension(width, ((width * 16) / 9) );
                break;
            case SQUARE:
                setMeasuredDimension(width,width);
                break;
            case LANDSCAPE:
                setMeasuredDimension(width, ((width * 9) / 16) );
                break;
        }
    }


    public void setUrl(String url){
        mUrl = url;
        loadFromUrl();
    }

    public String getUrl(){
        return mUrl;
    }

    public void setType(Type type){
        mType = type;
    }

    public Type getType(){
        return mType;
    }

    public void setSource(Source source){
        mSource = source;
    }

    public Source getSource(){
        return mSource;
    }

    private void loadFromUrl(){
        if( mSource == Source.PICASSO ){
            Picasso.with(getContext()).load(mUrl).into(new Target() {
                @Override
                public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                    setImageBitmap(bitmap);
                    invalidate();
                    requestLayout();
                }

                @Override
                public void onBitmapFailed(Drawable errorDrawable) {

                }

                @Override
                public void onPrepareLoad(Drawable placeHolderDrawable) {

                }
            });
        }else{
            Glide.with(getContext()).load(mUrl).listener(new RequestListener<String, GlideDrawable>() {
                @Override
                public boolean onException(Exception e, String model, com.bumptech.glide.request.target.Target<GlideDrawable> target, boolean isFirstResource) {
                    return false;
                }

                @Override
                public boolean onResourceReady(GlideDrawable resource, String model, com.bumptech.glide.request.target.Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                    invalidate();
                    requestLayout();
                    return false;
                }
            }).into(this);
        }

    }

}
