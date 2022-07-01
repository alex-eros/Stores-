package alex.eros.stores
import alex.eros.stores.databinding.ItemStoresBinding
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class StoreAdapter (private val stores:MutableList<Store>,private var listener:OnClickListener) :
    RecyclerView.Adapter<StoreAdapter.StoreHolder>() {

    private lateinit var mContext: Context


    inner class StoreHolder (view: View):RecyclerView.ViewHolder(view){
       var binding = ItemStoresBinding.bind(view)

       fun setListener(store:Store){
           binding.root.setOnClickListener{listener.Onclick(store)}
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
            setListener(store)
        }
    }

    override fun getItemCount(): Int = stores.size


    fun add(store: Store) {
        stores.add(store)
        notifyDataSetChanged()
    }
    /*Este metodo define el tamño de la lista en patalla, esto es el tamño de la lista que se le pasa
    a la clase que hace de adaptador*/

}