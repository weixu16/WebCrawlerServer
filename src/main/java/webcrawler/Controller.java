package webcrawler;

import java.io.File;

import org.apache.commons.io.FileUtils;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

public class Controller {
    public static void startCrawler(String seedUrl) throws Exception {
        String crawlStorageFolder = "/Users/wei/crawl";
        int numberOfCrawlers = 4;
        FileUtils.deleteDirectory(new File(crawlStorageFolder));

        CrawlConfig config = new CrawlConfig();
        config.setCrawlStorageFolder(crawlStorageFolder);
        //enable SSL
        config.setIncludeHttpsPages(true);
        //set the crawl depth
        config.setMaxDepthOfCrawling(6);
        //set the maximum number of pages to crawl
        config.setMaxPagesToFetch(6);
        //set Politeness
        config.setPolitenessDelay(2000);
        //resumable crawling
        config.setResumableCrawling(true);
        /*
         * Instantiate the controller for this crawl.
         */
        PageFetcher pageFetcher = new PageFetcher(config);
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
        CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);

        /*
         * For each crawl, you need to add some seed urls. These are the first
         * URLs that are ` and then the crawler starts following links
         * which are found in these pages
         */
        controller.addSeed(seedUrl);
        //controller.addSeed("http://www.uwb.edu/");
        //controller.addSeed("https://www.elastic.co/guide/index.html");

        /*
         * Start the crawl. This is a blocking operation, meaning that your code
         * will reach the line after this only when crawling is finished.
         */
        controller.start(MyCrawler.class, numberOfCrawlers);        
    }
}