package xyz.moviecast.streamer.listeners;

import com.frostwire.jlibtorrent.AlertListener;
import com.frostwire.jlibtorrent.DhtRoutingBucket;
import com.frostwire.jlibtorrent.SessionManager;
import com.frostwire.jlibtorrent.alerts.Alert;
import com.frostwire.jlibtorrent.alerts.AlertType;
import com.frostwire.jlibtorrent.alerts.DhtStatsAlert;

public abstract class DHTStatsAlertListener implements AlertListener {

    private SessionManager sessionManager;

    public DHTStatsAlertListener(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    public abstract void onStats(int totalNodes);

    @Override
    public int[] types() {
        return new int[] {AlertType.DHT_STATS.swig(), AlertType.SESSION_STATS.swig()};
    }

    @Override
    public void alert(Alert<?> alert) {
        if(alert.type().equals(AlertType.DHT_STATS)) {
//            DhtStatsAlert dhtStatsAlert = (DhtStatsAlert) alert;
//
//            int total = 0;
//
//            for(DhtRoutingBucket bucket : dhtStatsAlert.routingTable()) {
//                total += bucket.numNodes();
//            }

            onStats((int) sessionManager.stats().dhtNodes());
        }

        if(alert.type().equals(AlertType.SESSION_STATS)) {
            sessionManager.postDhtStats();
        }
    }
}
