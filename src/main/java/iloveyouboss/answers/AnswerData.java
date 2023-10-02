package iloveyouboss.answers;

import iloveyouboss.data.Data;
import iloveyouboss.functional.CheckedConsumer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

// TODO test
public class AnswerData extends Data<Answer> {
   private static final String TABLE_NAME = "Answer";
   private static final String ID_COLUMN = "id";

   public AnswerData() {
      super(TABLE_NAME, ID_COLUMN);
   }

   @Override
   public void createIfNotExists() {
      table.createIfNotExists(Answer.class, List.of("criterionId", "value"));
   }

   @Override
   protected Answer createFromRow(ResultSet results) throws SQLException {
      var id = results.getInt(ID_COLUMN);
      var criterionId = results.getInt("criterionId");
      var text = results.getString("value");
      return new Answer(id, criterionId, text);
   }

   @Override
   public int add(Answer answer) {
      return table.insert(new String[] {"text"}, convertRowToQuestion(answer));
   }

   @Override
   protected CheckedConsumer<PreparedStatement> convertRowToQuestion(Answer answer) {
      return statement -> {
         statement.setInt(1, answer.id());
         statement.setInt(2, answer.criterionId());
         statement.setString(3, answer.value());
      };
   }
}