// package com.fifteen;

// import static org.junit.jupiter.api.Assertions.assertEquals;

// import java.sql.ResultSet;
// import java.sql.SQLException;
// import java.util.ArrayList;

// import com.fifteen.events.local.EventLocal;
// import com.fifteen.events.local.localDb;
// import com.fifteen.events.local.localDbMethod;

// import org.junit.jupiter.api.AfterAll;
// import org.junit.jupiter.api.BeforeAll;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.TestInstance;

// @TestInstance(TestInstance.Lifecycle.PER_CLASS)
// public class ExportTxtTest {
// @BeforeAll
// void connectToLocalDB() {
// localDb.loadSqliteDriver();
// try {
// localDb.createLocalConncetion();
// } catch (SQLException e) {
// e.printStackTrace();
// }
// }

// @Test
// void StringEventTime() {
// try {
// ArrayList<String> result = localDbMethod.getAllEventTimeView();

// for (String row : result) {
// System.out.println(row);
// }
// } catch (SQLException e) {
// e.printStackTrace();
// }
// }

// @Test
// void Stringparticipants() {
// try {
// ArrayList<String> result = localDbMethod.getAllParticipants();
// String string = String.join("\n", result);

// System.out.println(string);

// } catch (SQLException e) {
// e.printStackTrace();
// }
// }

// @AfterAll
// void closeLocalConnection() {
// localDb.closeLocalConnection();
// }
// }
