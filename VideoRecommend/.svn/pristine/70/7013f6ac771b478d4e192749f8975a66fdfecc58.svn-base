package com.skyworth.entity.provider;

import java.util.ArrayList;

import com.skyworth.entity.Program;

/**
 * This class handles data related operation
 * 
 * @author Ni-Guanhua
 * 
 */
public final class ProgramProvider {

    /**
     * program categories
     */
    public static final String CATEGORY_MOVIE = "dy";
    public static final String CATEGORY_TEVEPLAY = "dsj";
    public static final String CATEGORY_SHOW = "zy";
    public static final String CATEGORY_CARTOON = "dm";

    private static Object mObject = new Object();
    // instance of ProgramProvider class
    private static ProgramProvider mProgramProvider = null;

    /**
     * various category program list
     */
    private ArrayList<Program> mMovies = null;
    private ArrayList<Program> mTeleplays = null;
    private ArrayList<Program> mShows = null;
    private ArrayList<Program> mCartoons = null;

    private ProgramProvider() {
        mMovies = new ArrayList<Program>(1);
        mTeleplays = new ArrayList<Program>(1);
        mShows = new ArrayList<Program>(1);
        mCartoons = new ArrayList<Program>(1);
    }

    /**
     * obtain ProgramProvider instance
     * 
     * @return ProgramProvider instance
     */
    public static ProgramProvider getInstance() {
        synchronized (mObject) {
            if (mProgramProvider == null) {
                mProgramProvider = new ProgramProvider();
            }
        }
        return mProgramProvider;
    }

    /**
     * Obtain program list by category
     * 
     * @param category
     *            value within CATEGORY_MOVIE, CATEGORY_TEVEPLAY, CATEGORY_SHOW
     *            and ATEGORY_CARTOON
     * @return program list according to category, else null returns
     */
    public ArrayList<Program> getProgramList(String category) {
        if (CATEGORY_MOVIE.equals(category)) {
            return mMovies;
        }
        if (CATEGORY_TEVEPLAY.equals(category)) {
            return mTeleplays;
        }
        if (CATEGORY_SHOW.equals(category)) {
            return mShows;
        }
        if (CATEGORY_CARTOON.equals(category)) {
            return mCartoons;
        }
        return null;
    }

    /**
     * Add one program into program list width category identifier
     * 
     * @param program
     *            program object added to list
     * @param category
     *            program category, value in CATEGORY_MOVIE, CATEGORY_TEVEPLAY,
     *            CATEGORY_SHOW and ATEGORY_CARTOON
     */
    public void addProgram(Program program, String category) {
        if (CATEGORY_MOVIE.equals(category)) {
            mMovies.add(program);
        }
        if (CATEGORY_TEVEPLAY.equals(category)) {
            mTeleplays.add(program);
        }
        if (CATEGORY_SHOW.equals(category)) {
            mShows.add(program);
        }
        if (CATEGORY_CARTOON.equals(category)) {
            mCartoons.add(program);
        }
    }

    /**
     * Find one program with its position in ui and its category
     * 
     * @param position
     *            position is view
     * @param category
     *            value in CATEGORY_MOVIE, CATEGORY_TEVEPLAY, CATEGORY_SHOW and
     *            ATEGORY_CARTOON
     * @return one detail Program instance
     */
    public Program findProgramByPosition(int position, String category) {
        Program program = null;
        if (CATEGORY_MOVIE.equals(category)) {
            program = mMovies.get(position);
        }
        if (CATEGORY_TEVEPLAY.equals(category)) {
            program = mTeleplays.get(position);
        }
        if (CATEGORY_SHOW.equals(category)) {
            program = mShows.get(position);
        }
        if (CATEGORY_CARTOON.equals(category)) {
            program = mCartoons.get(position);
        }
        return program;
    }
    
    /**
     * Clear all program list
     */
    public void clear() {
        if (null != mMovies) {
            mMovies.clear();
        }
        if (null != mTeleplays) {
            mTeleplays.clear();
        }
        if (null != mShows) {
            mShows.clear();
        }
        if (null != mCartoons) {
            mCartoons.clear();
        }
    }
}
