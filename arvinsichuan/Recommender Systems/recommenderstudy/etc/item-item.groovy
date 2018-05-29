import org.lenskit.api.ItemScorer
import org.lenskit.baseline.BaselineScorer
import org.lenskit.bias.BiasItemScorer
import org.lenskit.bias.BiasModel
import org.lenskit.bias.LiveUserItemBiasModel
import org.lenskit.bias.UserItemBiasModel
import org.lenskit.knn.MinNeighbors
import org.lenskit.knn.item.ItemItemScorer
import org.lenskit.transform.normalize.BiasUserVectorNormalizer
import org.lenskit.transform.normalize.UserVectorNormalizer


bind ItemScorer to ItemItemScorer.class
bind (BaselineScorer, ItemScorer) to BiasItemScorer
bind BiasModel to LiveUserItemBiasModel
bind (UserVectorNormalizer) to BiasUserVectorNormalizer

set MinNeighbors to 4

within (UserVectorNormalizer){
    bind BiasModel to UserItemBiasModel
}


