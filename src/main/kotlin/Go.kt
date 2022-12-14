import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun go(block: suspend () -> Unit) = CoroutineScope(Dispatchers.Default).launch { block() }
