package trellolite.model;

// ---------------------------------------------------------------------------------------------------------------------
// IMPORTS
// ---------------------------------------------------------------------------------------------------------------------

import java.io.Serializable;

/**
 * Role enum
 * This enum represents the role of a participant in the Trello-Lite application.
 * A participant can be an administrator, a member or an observer.
 * The role of a participant can be serialized and deserialized.
 *
 * @author Pierre Fromont Boissel
 * @see Participant
 * @see Serializable
 */
public enum Role {
    ADMIN, MEMBER, OBSERVER
}