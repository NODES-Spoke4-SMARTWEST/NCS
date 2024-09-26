package com.application.nodes.GroupManagement;

import com.application.nodes.UserManagement.User;

public interface GroupEventReciver {
    // Method called when a new group is added
    void updateGroupAdded(GroupManager groupMgr, Group newGroup);

    // Method called when a group is edited
    void updateGroupEdited(GroupManager groupMgr, Group currentGroup);

    // Method called when a participant is added to a group
    void updateParticipantAdded(GroupManager groupMgr, Group currentGroup, User user);
}
