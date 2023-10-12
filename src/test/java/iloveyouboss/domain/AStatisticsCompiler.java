package iloveyouboss.domain;

import iloveyouboss.domain.questions.YesNoQuestion;
import iloveyouboss.service.CriterionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static iloveyouboss.domain.questions.YesNoQuestion.No;
import static iloveyouboss.domain.questions.YesNoQuestion.Yes;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class AStatisticsCompiler {
   @InjectMocks
   StatisticsCompiler compiler;
   @Mock
   CriterionService criterionService;

   Question hasTuitionReimburse = new YesNoQuestion(1, "Tuition?");
   Question hasRelocation = new YesNoQuestion(2, "Relocation?");
   Criterion tuitionCriterion = new Criterion(hasTuitionReimburse.id(), Yes);
   Criterion relocationCriterion = new Criterion(hasRelocation.id(), Yes);

   @Test
   void producesAnswerCountsByQuestionTextHistogram() {
      var answers = List.of(
         annotatedAnswer(tuitionCriterion, hasTuitionReimburse, Yes),
         annotatedAnswer(tuitionCriterion, hasTuitionReimburse, Yes),
         annotatedAnswer(tuitionCriterion, hasTuitionReimburse, Yes),
         annotatedAnswer(tuitionCriterion, hasTuitionReimburse, No),
         annotatedAnswer(relocationCriterion, hasRelocation, Yes),
         annotatedAnswer(relocationCriterion, hasRelocation, Yes));

      var statistics = compiler.answerCountsByQuestionText(answers);

      assertEquals(3, statistics.get(hasTuitionReimburse.text()).get(Yes));
      assertEquals(1, statistics.get(hasTuitionReimburse.text()).get(No));
      assertEquals(2, statistics.get(hasRelocation.text()).get(Yes));
      assertEquals(0, statistics.get(hasRelocation.text()).get(No));
   }

   AnnotatedAnswer annotatedAnswer(
      Criterion criterion, Question question, String answer) {
      return new AnnotatedAnswer(
         new Answer(criterion.id(), answer), question.text());
   }
}
