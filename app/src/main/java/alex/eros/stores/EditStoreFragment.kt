package alex.eros.stores

import alex.eros.stores.databinding.FragmentEditStoreBinding
import android.content.Context
import android.os.Bundle
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class EditStoreFragment : Fragment() {

    private lateinit var binding: FragmentEditStoreBinding
    private var activityMain : MainActivity?=null

    /*Se crea la vista*/
    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?):View? {
        binding = FragmentEditStoreBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = arguments?.getLong(getString(R.string.arg_id_key),0)
        if(id != null && id != 0L){
            val idString = id.toString()
            Toast.makeText(activityMain,idString,Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(activityMain,id.toString(),Toast.LENGTH_SHORT).show()
        }

        activityMain = activity as? MainActivity
        activityMain?.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        activityMain?.supportActionBar?.title = getString(R.string.edti_fragment_tittle)

        setHasOptionsMenu(true)

        binding.imageUrl.addTextChangedListener {
            Glide.with(this)
                .load(binding.imageUrl.text.toString())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .into(binding.ImagePhoto)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.options_menu_fragment, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when(item.itemId){
            android.R.id.home ->{
                activity?.onBackPressed()
                true
            }
            R.id.action ->{
                val store = Store(storeName = binding.EDStoreName.text.toString().trim(),
                phone = binding.EDStorePhone.text.toString().trim(),
                webSite = binding.EDStoreWebsite.text.toString().trim(),
                photoUrl = binding.imageUrl.text.toString().trim())

                doAsync {
                    store.id = StoreApplication.databse.StoreDao().addStore(store)
                    uiThread {
                        activityMain?.addStore(store)
                        hideKeyboard()
//                        Snackbar.make(binding.root,
//                            "Store succesfully added",
//                            Snackbar.LENGTH_SHORT)
//                            .show()
                        Toast.makeText(activityMain,getString(R.string.store_succes_add),Toast.LENGTH_SHORT).show()
                        activityMain?.onBackPressed()
                    }
                }
                true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    private fun hideKeyboard(){
        val inn = activityMain?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inn.hideSoftInputFromWindow(requireView().windowToken,0)
    }

    override fun onDestroyView() {
        hideKeyboard()
        super.onDestroyView()
    }

    override fun onDestroy() {
        /*Ocultamos la flecha de retroceso*/
        activityMain?.supportActionBar?.setDisplayHomeAsUpEnabled(false)
        /*Reestablecemos el nombre de la aplicacion*/
        activityMain?.supportActionBar?.title = getString(R.string.app_name)

        /*Deshabilitamos la barra*/
        setHasOptionsMenu(false)

        activityMain?.fabState(true)

        super.onDestroy()
    }

}