package fr.univ_amu.heromanager.exceptions;

import fr.univ_amu.heromanager.model.race.Race;

/**
 * Exception usable when a race isn't supported by methods
 */
public class UnknownRaceException extends Exception {

    /**
     * 1st constructor, to set an existing race isn't handled
     *
     * @param race unknown race
     */
    public UnknownRaceException(Race race) {
        super("Unsupported race " + race);
    }

    /**
     * 2nd constructor, to set up custom string error
     *
     * @param err string error
     */
    public UnknownRaceException(String err) {
        super(err);
    }
}
