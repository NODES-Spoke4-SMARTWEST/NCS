package com.application.nodes.BullettinBoardManagement;

public interface BBEventReciver {
    /**
     * Called when a new announcement is added.
     *
     * @param bbMgr The BBManager that manages the announcements.
     * @param newA  The new announcement that has been added.
     */
    void updateAnnouncementAdded(BBManager bbMgr, Announcement newA);

    /**
     * Called when an announcement is opened.
     *
     * @param bbMgr      The BBManager that manages the announcements.
     * @param announcement The announcement that has been opened.
     */
    void updateAnnouncementOpen(BBManager bbMgr, Announcement announcement);

    /**
     * Called when an announcement is canceled.
     *
     * @param bbMgr      The BBManager that manages the announcements.
     * @param announcement The announcement that has been canceled.
     */
    void updateAnnouncementCanceled(BBManager bbMgr, Announcement announcement);
}
