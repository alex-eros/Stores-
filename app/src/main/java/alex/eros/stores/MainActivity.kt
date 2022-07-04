package alex.eros.stores
import alex.eros.stores.databinding.ActivityMainBinding
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread


class MainActivity : AppCompatActivity(), OnClickListener, MainAux {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mAdapter:StoreAdapter
    private lateinit var mGridlayout: GridLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*Inicializamos binding y agregamos la referencia de la vista*/
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setRecyclerView()

//        binding.ButtonSave.setOnClickListener{
//            val store = Store(storeName = binding.ETStoreName.text.toString().trim())
//
//            /*Los procesos que se hagan con las bases de datos es necesario realizarlos
//            * en hilos paralelos al principal por que podrían provocar que la aplicacion se
//            * congele por cierto tiempo*/
//            Thread{
//                StoreApplication.databse.StoreDao().addStore(store)
//            }.start()
//            mAdapter.add(store)
//        }

        binding.fabAdd.setOnClickListener {
            launchEditFragment()
        }

    }

    private fun launchEditFragment() {
        val fragment = EditStoreFragment()

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        fragmentTransaction.add(R.id.ContainerMain, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()

        //binding.fabAdd.hide()
        fabState()
    }

    private fun setRecyclerView() {
        /*Inicializamos el adapter*/
        mAdapter = StoreAdapter(mutableListOf(),this)
        /*Inicializamos el GridLayputManager*/
        mGridlayout = GridLayoutManager(this,2)
        getStores()

        binding.RVStore.apply {
            setHasFixedSize(true)
            /*Asignamos el adapter y el layoutManager a nuestro RecyclerView*/
            adapter = mAdapter
            layoutManager = mGridlayout

        }
    }

     private fun getStores(){
        doAsync{
            /*Consulta a la base de datos en un hilo alterno*/
            val stores = StoreApplication.databse.StoreDao().getAllStores()
            uiThread {
                /*Agregando el arrya obtenido en la consulta al hilo principal*/
                mAdapter.setStores(stores)
            }
        }
    }

    override fun Onclick(store:Store) {

    }

    override fun OnFavoriteStore(store: Store) {
         store.isFavorite = !store.isFavorite
         doAsync {
             StoreApplication.databse.StoreDao().updateStores(store)
             uiThread {
                 mAdapter.update(store)
             }
         }
    }

    override fun OnDeleteStore(store: Store) {
        doAsync {
            StoreApplication.databse.StoreDao().deleteStore(store)
            uiThread {
                mAdapter.delete(store)
            }
        }
    }

    /*MainAux*/
    override fun fabState(isVisible: Boolean) {
        if (isVisible == true){
            binding.fabAdd.show()
        }else{
            binding.fabAdd.hide()
        }
    }

    override fun addStore(store: Store) {
       mAdapter.add(store)
    }

    override fun updateStore(store: Store) {

    }
}