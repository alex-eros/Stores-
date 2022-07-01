package alex.eros.stores
import alex.eros.stores.databinding.ActivityMainBinding
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager


class MainActivity : AppCompatActivity(), OnClickListener {

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

        binding.ButtonSave.setOnClickListener{
            val store = Store(storeName = binding.ETStoreName.text.toString().trim())
            mAdapter.add(store)
        }

    }

    private fun setRecyclerView() {
        /*Inicializamos el adapter*/
        mAdapter = StoreAdapter(mutableListOf(),this)
        /*Inicializamos el GridLayputManager*/
        mGridlayout = GridLayoutManager(this,2)

        binding.RVStore.apply {
            setHasFixedSize(true)
            /*Asignamos el adapter y el layoutManager a nuestro RecyclerView*/
            adapter = mAdapter
            layoutManager = mGridlayout

        }
    }

    override fun Onclick(store:Store) {

        

    }
}