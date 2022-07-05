package alex.eros.stores
import alex.eros.stores.databinding.ItemStoresBinding
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

class StoreAdapter (private var stores:MutableList<Store>,private var listener:OnClickListener) :
    RecyclerView.Adapter<StoreAdapter.StoreHolder>() {

    private lateinit var mContext: Context


    inner class StoreHolder (view: View):RecyclerView.ViewHolder(view){
       var binding = ItemStoresBinding.bind(view)

       fun setListener(store:Store){
           with(binding.root){
               setOnClickListener{listener.Onclick(store.id)}
               setOnLongClickListener {
                   listener.OnDeleteStore(store)
               true}
           }
           binding.CheckBoxFavorite.setOnClickListener {
               listener.OnFavoriteStore(store)
           }
       }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreHolder {
        mContext = parent.context
        val view = LayoutInflater.from(mContext).inflate(R.layout.item_stores,parent, false)
        return StoreHolder(view)
        /*En este metodo le decimos al adapter cual es la vista que debe inflar para cada
        * item del RecyclerView*/
    }

    override fun onBindViewHolder(holder: StoreHolder, position: Int) {
        val store = stores.get(position)

        with(holder){
            binding.TVName.text = store.storeName
            binding.CheckBoxFavorite.isChecked = store.isFavorite

            Glide.with(mContext)
                .load(store.photoUrl)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(binding.ImagePhoto)

            setListener(store)
        }
    }

    override fun getItemCount(): Int = stores.size
    /*Este metodo define el tamño de la lista en patalla, esto es el tamño de la lista que se le pasa
    a la clase que hace de adaptador*/



    fun add(store: Store) {
        if(!stores.contains(store)){
            stores.add(store)
            notifyItemInserted(stores.size-1)
        }
    }

    fun setStores(stores: MutableList<Store>) {
        /*Metodo que setea el RV en el inicio de la aplicaion mediante una consulta a la base de
        * datos*/
        this.stores = stores
        notifyDataSetChanged()
    }

    fun update(store: Store) {
        val index = stores.indexOf(store)
        if (index != -1){
            stores.set(index,store)
            notifyItemChanged(index)
        }
    }

    fun delete(store: Store) {
        val index = stores.indexOf(store)
        if (index != -1){
            stores.removeAt(index)
            notifyItemRemoved(index)
        }
    }

}