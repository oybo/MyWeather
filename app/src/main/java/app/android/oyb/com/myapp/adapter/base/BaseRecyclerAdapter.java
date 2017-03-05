package app.android.oyb.com.myapp.adapter.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chanven.lib.cptr.recyclerview.RecyclerViewHolder;
import com.chanven.lib.cptr.widget.ProgressWheel;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import app.android.oyb.com.myapp.R;

/**
 * Created by O on 2016/11/1.
 */

public abstract class BaseRecyclerAdapter<T> extends RecyclerView.Adapter<RecyclerViewHolder> {

    public static final int TYPE_FOOTER = Integer.MIN_VALUE;
    public static final int TYPE_ITEM = 0;
    private boolean hasFooter;//设置是否显示Footer
    private boolean hasMoreData;//设置是否可以继续加载数据

    private final List<T> mData = new LinkedList<T>();
    protected final Context mContext;
    protected LayoutInflater mInflater;
    protected OnItemClickListener mClickListener;
    protected OnItemLongClickListener mLongClickListener;

    public BaseRecyclerAdapter(Context ctx) {
        mContext = ctx;
        mInflater = LayoutInflater.from(ctx);
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_FOOTER) {//底部 加载view
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_load_more, parent, false);
            return new FooterViewHolder(parent.getContext(), view);
        } else {
            //数据itemViewHolder
            return onCreateItemViewHolder(parent, viewType);
        }
    }

    public static class FooterViewHolder extends RecyclerViewHolder {
        public final ProgressWheel mProgressView;
        public final TextView mTextView;

        public FooterViewHolder(Context ctx, View itemView) {
            super(ctx, itemView);
            mProgressView = (ProgressWheel) itemView.findViewById(R.id.progress_view);
            mTextView = (TextView) itemView.findViewById(R.id.tv_content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mTextView.getText();
        }
    }

    public ProgressWheel mProgressView;
    public TextView mTextView;

    protected void setHolderListenner(final RecyclerViewHolder holder) {
        if (mClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mClickListener.onItemClick(holder.itemView, holder.getLayoutPosition());
                }
            });
        }
        if (mLongClickListener != null) {
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mLongClickListener.onItemLongClick(holder.itemView, holder.getLayoutPosition());
                    return true;
                }
            });
        }
    }

    private RecyclerViewHolder onCreateItemViewHolder(ViewGroup parent, int viewType) {
        final RecyclerViewHolder holder = new RecyclerViewHolder(mContext, mInflater.inflate(getItemLayoutId
                (viewType), parent, false));

        setHolderListenner(holder);

        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        if (holder instanceof FooterViewHolder) {
            if (holder.itemView.getLayoutParams() instanceof StaggeredGridLayoutManager.LayoutParams) {
                StaggeredGridLayoutManager.LayoutParams clp = (StaggeredGridLayoutManager.LayoutParams) holder
                        .itemView.getLayoutParams();
                if (clp != null) {
                    clp.setFullSpan(true);
                }
            }
        }

        if (holder instanceof FooterViewHolder) {
            //没有更多数据
            if (hasMoreData) {
                ((FooterViewHolder) holder).mProgressView.setVisibility(View.VISIBLE);
                  ((FooterViewHolder) holder).mProgressView.spin();
                //((FooterViewHolder) holder).mProgressView.setIndeterminate(true);
                ((FooterViewHolder) holder).mTextView.setText("正在加载。。。");
            } else {
                   ((FooterViewHolder) holder).mProgressView.stopSpinning();
                ((FooterViewHolder) holder).mProgressView.setVisibility(View.GONE);
                //((FooterViewHolder) holder).mProgressView.st;
                ((FooterViewHolder) holder).mTextView.setText("没有更多数据了。。。。");
            }
        } else {
            bindData(holder, position, mData.get(position));

            addInternalClickListener(holder.itemView, position);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == getBasicItemCount() && hasFooter) {
            return TYPE_FOOTER;
        }
        return TYPE_ITEM;//0
    }

    public List<T> getList() {
        return mData;
    }

    public void append(T t) {
        if (t == null) {
            return;
        }
        mData.add(t);
    }

    public void appendToTop(T item) {
        if (item == null) {
            return;
        }
        mData.add(0, item);
    }

    public void appendToList(List<T> list) {
        if (list == null) {
            return;
        }
        mData.addAll(list);
    }

    public void appendToTopList(List<T> list) {
        if (list == null) {
            return;
        }
        mData.addAll(0, list);
    }


    public void remove(int position) {
        if (mData.size() > 0 && position >= 0) {
            mData.remove(position);
        }
    }

    public void clear() {
        mData.clear();
    }

    private int getBasicItemCount() {
        return mData.size();
    }

    @Override
    public int getItemCount() {
        return getBasicItemCount() + (hasFooter ? 1 : 0);
    }

    public T getItem(int position) {
        if (position > mData.size() - 1) {
            return null;
        }
        return mData.get(position);
    }

    // adapter中的内部点击事件
    public Map<Integer, onInternalClickListener> canClickItem;

    private void addInternalClickListener(final View itemV, final Integer position) {
        if (canClickItem != null) {
            for (Integer key : canClickItem.keySet()) {
                View inView = itemV.findViewById(key);
                final onInternalClickListener inviewListener = canClickItem.get(key);
                if (inView != null && inviewListener != null) {
                    inView.setOnClickListener(new View.OnClickListener() {

                        public void onClick(View v) {
                            inviewListener.OnClickListener(itemV, v, position);
                        }
                    });
                }
            }
        }
    }

    public void setOnInViewClickListener(Integer key, onInternalClickListener onClickListener) {
        if (canClickItem == null)
            canClickItem = new HashMap<Integer, onInternalClickListener>();
        canClickItem.put(key, onClickListener);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mClickListener = listener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener listener) {
        mLongClickListener = listener;
    }

    abstract public int getItemLayoutId(int viewType);

    abstract public void bindData(RecyclerViewHolder holder, int position, T item);

    public boolean hasFooter() {
        return hasFooter;
    }

    public void setHasFooter(boolean hasFooter) {
        if (this.hasFooter != hasFooter) {
            this.hasFooter = hasFooter;
            notifyDataSetChanged();
        }
    }


    public boolean hasMoreData() {
        return hasMoreData;
    }

    public void setHasMoreData(boolean isMoreData) {
        if (this.hasMoreData != isMoreData) {
            this.hasMoreData = isMoreData;
            notifyDataSetChanged();
        }
    }

    public void setHasMoreDataAndFooter(boolean hasMoreData, boolean hasFooter) {
        if (this.hasMoreData != hasMoreData || this.hasFooter != hasFooter) {
            this.hasMoreData = hasMoreData;
            this.hasFooter = hasFooter;
            notifyDataSetChanged();
        }
    }

    public interface OnItemClickListener {
        public void onItemClick(View itemView, int pos);
    }

    public interface OnItemLongClickListener {
        public void onItemLongClick(View itemView, int pos);
    }

    public interface onInternalClickListener {
        public void OnClickListener(View parentV, View v, Integer position);
    }
}