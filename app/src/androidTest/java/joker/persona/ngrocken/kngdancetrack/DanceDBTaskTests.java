package joker.persona.ngrocken.kngdancetrack;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import joker.persona.ngrocken.kngdancetrack.database.DanceDBTasks;
import joker.persona.ngrocken.kngdancetrack.model.Dance;
import joker.persona.ngrocken.kngdancetrack.util.DanceConsumer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class DanceDBTaskTests extends InsTestTemplate {

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();
        assertEquals("joker.persona.ngrocken.kngdancetrack", appContext.getPackageName());
    }

    @Test
    public void clearDance() {
        waitUntilFree();
        logTitle("clearDance()");
        final Context appContext = InstrumentationRegistry.getTargetContext();
        DanceDBTasks.getAllDances(appContext, new DanceConsumer<List<Dance>>() {
            @Override
            public void consume(List<Dance> dances) {
                if(dances.size() == 0) {
                    logMessage("NO Dances Found");
                    Dance dance = new Dance("TITLE", "CATEGORY", "DESCRIPTION");
                    DanceDBTasks.insertDance(appContext, new DanceConsumer<Long>() {
                        @Override
                        public void consume(Long aLong) {
                            logMessage("insertDance complete - " + aLong);
                            DanceDBTasks.clearDanceTable(appContext, new DanceConsumer<Void>() {
                                @Override
                                public void consume(Void aVoid) {
                                    logMessage("clearDance complete");
                                    DanceDBTasks.getAllDances(appContext, new DanceConsumer<List<Dance>>() {
                                        @Override
                                        public void consume(List<Dance> dances) {
                                            logMessage("getAllDances complete - " + dances.size());
                                            assertEquals(0, dances.size());
                                            logMessage("clearDanceTest Complete");
                                        }

                                        @Override
                                        public void handleError() {
                                            assertEquals(getError(), "");
                                        }
                                    });
                                }

                                @Override
                                public void handleError() {
                                    assertEquals(getError(), "");
                                }
                            });
                        }

                        @Override
                        public void handleError() {
                            assertEquals(getError(), "");
                        }
                    }, dance);
                } else {
                    logMessage("Found " + dances.size() + " dance(s)");
                    DanceDBTasks.clearDanceTable(appContext, new DanceConsumer<Void>() {

                        @Override
                        public void consume(Void aVoid) {
                            logMessage("clearDanceTable complete");
                            DanceDBTasks.getAllDances(appContext, new DanceConsumer<List<Dance>>() {
                                @Override
                                public void consume(List<Dance> dances) {
                                    logMessage("getAllDances complete");
                                    assertEquals(0, dances.size());
                                    logMessage("clearDanceTest Complete!");
                                }

                                @Override
                                public void handleError() {
                                    assertEquals(getError(), "");
                                }
                            });
                        }

                        @Override
                        public void handleError() {
                            assertEquals(getError(), "");
                        }
                    });
                }
            }

            @Override
            public void handleError() {
                logMessage("getAllDances error - " + getError());
                assertTrue(false);
            }
        });
    }

    @Test
    public void insertDance() {
        waitUntilFree();
        logTitle("insertDance");
        final Context appContext = InstrumentationRegistry.getTargetContext();

        final String danceName = "Test Dance";
        final String description = "Hello World";
        final String category = "Swing";

        final Dance dance = new Dance(danceName, category, description);
        DanceDBTasks.clearDanceTable(appContext, new DanceConsumer<Void>() {
            @Override
            public void consume(Void aVoid) {
                DanceDBTasks.insertDance(appContext, new DanceConsumer<Long>() {
                    @Override
                    public void consume(Long aLong) {
                        assertNotEquals(aLong, new Long(0L));
                        DanceDBTasks.getDanceById(appContext, new DanceConsumer<Dance>() {
                            @Override
                            public void consume(Dance dance) {
                                assertNotNull(dance);
                                assertEquals(dance.getName(), danceName);
                                assertEquals(dance.getCategory(), category);
                                assertEquals(dance.getDescription(), description);
                            }

                            @Override
                            public void handleError() {
                                assertEquals(getError(), "");
                            }
                        }, aLong);
                    }

                    @Override
                    public void handleError() {
                        assertEquals(getError(), "");
                    }
                }, dance);
            }

            @Override
            public void handleError() {
                assertEquals(getError(), "");
            }
        });
    }

//    @Test
//    public void insertDuplicateDance() {
//        Log.d("DuplicateDance", "HELLO");
//        final Context appContext = InstrumentationRegistry.getTargetContext();
//
//        final String danceName = "TEST DANCE";
//        final String category = "category";
//        final String description = "description";
//        final Dance dance = new Dance(danceName, category, description);
//        DanceDBTasks.clearDanceTable(appContext, new DanceConsumer<Void>() {
//            @Override
//            public void consume(Void aVoid) {
//                DanceDBTasks.insertDance(appContext, new DanceConsumer<Long>() {
//                    @Override
//                    public void consume(Long aLong) {
//                        assertNotNull(aLong);
//                        assertNotEquals(new Long(0), aLong);
//                        DanceDBTasks.insertDance(appContext, new DanceConsumer<Long>() {
//                            @Override
//                            public void consume(Long aLong) {
//                                assertNotEquals("", "");
//                            }
//
//                            @Override
//                            public void handleError() {
//                                assertNotEquals(getError(), "asdf");
//                                DanceDBTasks.getAllDances(appContext, new DanceConsumer<List<Dance>>() {
//                                    @Override
//                                    public void consume(List<Dance> dances) {
//                                        assertEquals(dances.size(), 1);
//                                    }
//
//                                    @Override
//                                    public void handleError() {
//
//                                    }
//                                });
//                            }
//                        }, dance);
//                    }
//
//                    @Override
//                    public void handleError() {
//                        assertEquals(getError(), "");
//                    }
//                }, dance);
//            }
//
//            @Override
//            public void handleError() {
//                assertEquals(getError(), "");
//            }
//        });
//
//    }
//
//    @Test
//    public void insertDanceMove() {
//        final Context appContext = InstrumentationRegistry.getTargetContext();
//        final String name = "Whip";
//        final long danceId = 1L;
//        final String danceName = "West Coast Swing";
//        final String description = "A Basic Whip";
//
//        Move move = new Move(name, danceId, danceName, description);
//        DanceObjectDBTasks.insertDanceMove(appContext, move, new DanceConsumer<Long>() {
//            @Override
//            public void consume(Long aLong) {
//                assertNotNull(aLong);
//                DanceObjectDBTasks.getDanceMoveById(appContext, aLong, new DanceConsumer<Move>() {
//
//                    @Override
//                    public void consume(Move move) {
//                        assertNotNull(move);
//                        assertEquals(move.getName(), name);
//                        assertEquals(move.getDanceId(), danceId);
//                        assertEquals(move.getDanceName(), danceName);
//                        assertEquals(move.getDescription(), description);
//                    }
//
//                    @Override
//                    public void handleError() {
//                        assertEquals(getError(), "");
//                    }
//                });
//            }
//
//            @Override
//            public void handleError() {
//                assertEquals(getError(), "");
//            }
//        });
//
//    }
}

