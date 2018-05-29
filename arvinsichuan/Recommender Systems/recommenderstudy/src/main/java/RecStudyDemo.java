import org.lenskit.LenskitConfiguration;
import org.lenskit.LenskitRecommender;
import org.lenskit.LenskitRecommenderEngine;
import org.lenskit.api.ItemRecommender;
import org.lenskit.api.ResultList;
import org.lenskit.config.ConfigHelpers;
import org.lenskit.data.dao.file.StaticDataSource;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

/**
 * Project recommenderstudy
 *
 * @author: arvinsichuan
 * <p>
 * Date: 18-May-18
 * <p>
 * Package: PACKAGE_NAME
 */
public class RecStudyDemo {
    public static void main(String[] args) {
        LenskitConfiguration configuration = null;
        StaticDataSource dataSource = null;
        try {
            configuration = ConfigHelpers.load(new File("etc/item-item.groovy"));
            dataSource = StaticDataSource.load(Paths.get("data/movielens.yml"));

        } catch (IOException e) {
            e.printStackTrace();
        }

        assert dataSource != null;
        LenskitRecommenderEngine engine = LenskitRecommenderEngine.build(configuration, dataSource.get());
        try (LenskitRecommender recommender = engine.createRecommender(dataSource.get())) {
            ItemRecommender itemRecommender = recommender.getItemRecommender();
            assert itemRecommender != null;
            ResultList results = itemRecommender.recommendWithDetails(42, 10, null, null);
            results.forEach(System.out::println);
        }


    }
}

