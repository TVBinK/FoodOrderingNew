import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.foododering.databinding.PopularItemCartBinding


class AdapterCart(
    private val items: MutableList<String>,
    private val images: MutableList<Int>,
    private val prices: MutableList<String>,
    private val subtotal: TextView,
    private val total: TextView
) :
    RecyclerView.Adapter<AdapterCart.PopularViewHolder>() {
    private var Sum=0
    private var itemQuantities = IntArray(items.size) { 1 }
    override fun onBindViewHolder(holder: PopularViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularViewHolder {
        return PopularViewHolder(
            PopularItemCartBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }


    inner class PopularViewHolder(private var binding: PopularItemCartBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.apply {
                tvFoodName.text = items[position]
                tvPrice.text = prices[position]
                imgViewItem.setImageResource(images[position])
                tvQuantity.text = itemQuantities[position].toString()
                Sum = calculateTotalPrice()
                btnMinus.setOnClickListener {
                    if (itemQuantities[position] > 0) {
                        itemQuantities[position]--
                        tvQuantity.text = itemQuantities[position].toString()
                        Sum-=prices[position].toInt()
                        subtotal.text=(Sum-10).toString()
                        total.text = Sum.toString()
                    } 
                }
                btnPlus.setOnClickListener {
                    itemQuantities[position]++
                    tvQuantity.text = itemQuantities[position].toString()
                    Sum+=prices[position].toInt()
                    subtotal.text=(Sum-10).toString()
                    total.text = Sum.toString()
                }
                btnDelete.setOnClickListener {
                    subtotal.text=(Sum-prices[position].toInt()*itemQuantities[position]-10).toString()
                    total.text = (Sum-prices[position].toInt()*itemQuantities[position]).toString()
                    ItemDelete(position)
                }

            }
        }

    }

    fun ItemDelete(position: Int) {
        items.removeAt(position)
        prices.removeAt(position)
        images.removeAt(position)
        var newArrQuantities = removeElement(itemQuantities,position)
        for(i in 0..newArrQuantities.size-1){
            itemQuantities[i]=newArrQuantities[i]
        }
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, items.size)
    }
    fun calculateTotalPrice(): Int {
        var totalPrice = 10
        for (i in items.indices) {
            val price = prices.getOrNull(i)?.toIntOrNull() ?: continue
            totalPrice += price * itemQuantities[i]
        }
        return totalPrice
    }

    fun calculateTotalSubPrice(): Int {
        var totalPrice = 0
        for (i in items.indices) {
            val price = prices.getOrNull(i)?.toIntOrNull() ?: continue
            totalPrice += price * itemQuantities[i]
        }
        return totalPrice
    }

    fun removeElement(array: IntArray, index: Int): IntArray {
        if (index == array.size-1) {
            return array.copyOfRange(0, index-2)
        }
              return array.copyOfRange(0, index) + array.copyOfRange(index + 1, array.size-1)
    }

}