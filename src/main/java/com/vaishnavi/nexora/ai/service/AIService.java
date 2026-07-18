package com.vaishnavi.nexora.ai.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;
import com.vaishnavi.nexora.entity.User;
import com.vaishnavi.nexora.repository.UserRepository;
import com.vaishnavi.nexora.task.entity.Task;
import com.vaishnavi.nexora.task.repository.TaskRepository;

import org.springframework.data.domain.PageRequest;
import java.util.List;
import java.util.Map;

@Service
public class AIService {

    private final TaskRepository taskRepository;

    private final UserRepository userRepository;

    private final RestClient groqClient;

    @Value("${groq.api.model}")
    private String model;

// CONSTRUCTOR

    // CONSTRUCTOR

    public AIService(
            TaskRepository taskRepository,
            UserRepository userRepository,
            RestClient groqClient
    ) {

        this.taskRepository = taskRepository;

        this.userRepository = userRepository;

        this.groqClient = groqClient;
    }

// COMMON AI REQUEST METHOD

    private String sendAIRequest(String prompt) {

        Map<String, Object> requestBody = Map.of(

                "model",
                model,

                "messages",
                List.of(

                        Map.of(
                                "role",
                                "user",

                                "content",
                                prompt
                        )
                )
        );

        try {

            GroqResponse response =
                    groqClient
                            .post()
                            .uri("/chat/completions")
                            .contentType(MediaType.APPLICATION_JSON)
                            .body(requestBody)
                            .retrieve()
                            .body(GroqResponse.class);


            if (response == null
                    || response.choices() == null
                    || response.choices().isEmpty()
                    || response.choices().get(0).message() == null) {

                return "Unable to generate AI response.";
            }

            return response
                    .choices()
                    .get(0)
                    .message()
                    .content();


        } catch (
                HttpClientErrorException.TooManyRequests e
        ) {

            return "AI service quota is currently unavailable.";


        } catch (
                HttpClientErrorException.Unauthorized e
        ) {

            return "AI service authentication failed.";


        } catch (
                HttpClientErrorException.BadRequest e
        ) {

            return "Invalid request sent to the AI service.";


        } catch (
                HttpClientErrorException e
        ) {

            return "AI service error. Please try again later.";


        } catch (
                RestClientException e
        ) {

            return "Unable to connect to the AI service.";


        } catch (Exception e) {

            return "Unable to generate AI response.";
        }
    }

// NOTE AI - SUMMARIZATION

    public String summarizeNote(
            String noteContent
    ) {

        if (
                noteContent == null
                        || noteContent.isBlank()
        ) {

            return "Note content is empty.";
        }


        String prompt = """

                Summarize the following note clearly and concisely.

                Provide:
                1. A short summary
                2. The main key points
                3. Important action items if present

                Do not invent information.

                Note:
                %s

                """.formatted(noteContent);


        return sendAIRequest(prompt);
    }

// TASK AI - PRIORITIZATION

    public String prioritizeTask(

            String title,

            String description,

            String status,

            String priority,

            String dueDate

    ) {


        String taskDetails = """

                Task Title: %s
                Description: %s
                Current Status: %s
                Current Priority: %s
                Due Date: %s

                """.formatted(

                title,

                description != null
                        ? description
                        : "No description",

                status,

                priority,

                dueDate != null
                        ? dueDate
                        : "No due date"
        );


        String prompt = """

                Analyze the following task and provide a concise prioritization recommendation.

                Consider:
                - Urgency
                - Due date
                - Current priority
                - Current status

                Provide:
                1. Recommended priority: HIGH, MEDIUM, or LOW
                2. Short reason
                3. Practical next step

                Task details:

                %s

                """.formatted(taskDetails);


        return sendAIRequest(prompt);
    }

// EXPENSE AI - INSIGHTS

    public String analyzeExpense(

            String title,

            String amount,

            String category,

            String expenseDate,

            String paymentMethod,

            String description

    ) {

        String expenseDetails = """

                Expense Title: %s
                Amount: %s
                Category: %s
                Expense Date: %s
                Payment Method: %s
                Description: %s

                """.formatted(

                title,

                amount,

                category,

                expenseDate,

                paymentMethod != null
                        ? paymentMethod
                        : "Not specified",

                description != null
                        ? description
                        : "No description"
        );

        String prompt = """

                Analyze the following personal expense and provide a concise financial insight.

                Consider:
                - Expense category
                - Amount
                - Whether it appears essential or discretionary
                - A practical saving suggestion

                Important:
                - Do not assume the user's complete financial situation.
                - Do not provide professional financial advice.
                - Base the analysis only on the provided information.

                Expense details:

                %s

                """.formatted(expenseDetails);


        return sendAIRequest(prompt);
    }

// GROCERY AI - SMART SUGGESTIONS

    public String analyzeGrocery(

            String itemName,

            String quantity,

            String unit,

            String category,

            String status,

            String priority,

            String estimatedPrice,

            String actualPrice

    ) {

        String groceryDetails = """

                Item Name: %s
                Quantity: %s
                Unit: %s
                Category: %s
                Status: %s
                Priority: %s
                Estimated Price: %s
                Actual Price: %s

                """.formatted(

                itemName,

                quantity != null
                        ? quantity
                        : "Not specified",

                unit != null
                        ? unit
                        : "Not specified",

                category != null
                        ? category
                        : "Not specified",

                status,

                priority,

                estimatedPrice != null
                        ? estimatedPrice
                        : "Not specified",

                actualPrice != null
                        ? actualPrice
                        : "Not purchased yet"
        );

        String prompt = """

                Analyze the following grocery item and provide a concise smart shopping suggestion.

                Consider:
                - Quantity
                - Status
                - Priority
                - Estimated price
                - Actual price
                - Category

                Provide:
                1. A useful shopping insight
                2. A practical suggestion

                Do not invent information.

                Grocery details:

                %s

                """.formatted(groceryDetails);


        return sendAIRequest(prompt);
    }

// DIET AI - NUTRITION INSIGHTS

    public String analyzeDiet(

            String mealType,

            String foodName,

            String quantity,

            String calories,

            String protein,

            String carbs,

            String fats,

            String mealTime

    ) {

        String dietDetails = """

                Meal Type: %s
                Food Name: %s
                Quantity: %s
                Calories: %s
                Protein: %s
                Carbohydrates: %s
                Fats: %s
                Meal Time: %s

                """.formatted(

                mealType,

                foodName,

                quantity != null
                        ? quantity
                        : "Not specified",

                calories != null
                        ? calories
                        : "Not specified",

                protein != null
                        ? protein
                        : "Not specified",

                carbs != null
                        ? carbs
                        : "Not specified",

                fats != null
                        ? fats
                        : "Not specified",

                mealTime != null
                        ? mealTime
                        : "Not specified"
        );

        String prompt = """

                Analyze the following meal and provide a concise general nutrition insight.

                Provide:
                1. A brief nutritional observation
                2. One practical suggestion for improving or balancing the meal

                Important:
                - Do not diagnose medical conditions.
                - Do not prescribe medication or treatment.
                - Base the analysis only on the provided meal information.

                Meal details:

                %s

                """.formatted(dietDetails);


        return sendAIRequest(prompt);
    }

// HEALTH AI - WELLNESS INSIGHTS

    public String analyzeHealth(

            String weight,

            String height,

            String bmi,

            String waterIntake,

            String sleepHours,

            String steps,

            String exerciseMinutes,

            String recordDate

    ) {

        String healthDetails = """

                Weight: %s kg
                Height: %s cm
                BMI: %s
                Water Intake: %s litres
                Sleep Hours: %s
                Steps: %s
                Exercise Minutes: %s
                Record Date: %s

                """.formatted(

                weight,

                height,

                bmi != null
                        ? bmi
                        : "Not calculated",

                waterIntake != null
                        ? waterIntake
                        : "Not specified",

                sleepHours != null
                        ? sleepHours
                        : "Not specified",

                steps != null
                        ? steps
                        : "Not specified",

                exerciseMinutes != null
                        ? exerciseMinutes
                        : "Not specified",

                recordDate != null
                        ? recordDate
                        : "Not specified"
        );

        String prompt = """

                Analyze the following daily health record and provide concise general wellness insights.

                Consider:
                - BMI as a general wellness indicator
                - Hydration
                - Sleep
                - Daily steps
                - Exercise activity
                - Overall lifestyle balance

                Provide:
                1. Overall wellness insight
                2. Positive habits
                3. Areas for improvement
                4. Practical wellness suggestions

                Important:
                - Do not diagnose medical conditions.
                - Do not prescribe medication or treatment.
                - Do not make alarming conclusions.
                - BMI observations should be presented cautiously.
                - This is general wellness guidance, not medical advice.

                Health record:

                %s

                """.formatted(healthDetails);

        return sendAIRequest(prompt);
    }

// DOCUMENT AI - DOCUMENT ANALYSIS

    public String analyzeDocument(

            String title,

            String category,

            String description,

            String fileType,

            String extractedText

    ) {

        String documentDetails = """

                Document Title: %s
                Category: %s
                Description: %s
                File Type: %s

                Document Content:

                %s

                """.formatted(

                title,

                category != null
                        ? category
                        : "Not specified",

                description != null
                        ? description
                        : "No description",

                fileType != null
                        ? fileType
                        : "Unknown",

                extractedText
        );

        String prompt = """

                Analyze the following personal document.

                Provide:

                1. A concise summary
                2. Key points
                3. Important information or deadlines
                4. Action items
                5. One practical observation

                Important:
                - Base the analysis only on the document content.
                - Do not invent information.
                - Do not provide legal, medical, or financial advice.
                - If information is unclear, mention that clearly.
                - Keep the response clear and well structured.

                Document details:

                %s

                """.formatted(documentDetails);


        return sendAIRequest(prompt);
    }

// GROQ RESPONSE RECORDS

    public record GroqResponse(

            List<Choice> choices

    ) {
    }


    public record Choice(

            Message message

    ) {
    }


    public record Message(

            String content

    ) {
    }
// CENTRAL AI - NEXORA ASSISTANT

    public String centralChat(String userMessage) {

        if (userMessage == null || userMessage.isBlank()) {
            return "Please enter a message.";
        }

        String prompt = """

            You are Nexora AI, the intelligent central assistant of the Nexora
            AI-Powered Life Operating System.

            Nexora helps users manage:
            - Tasks
            - Notes
            - Expenses
            - Groceries
            - Calendar
            - Health
            - Diet
            - Documents

            Answer the user's question clearly, practically, and concisely.

            Important rules:
            - Do not invent personal data.
            - If you do not have enough information, say so clearly.
            - Give practical and actionable suggestions.
            - For health and diet topics, provide general wellness information only.
            - Do not diagnose medical conditions.
            - Do not prescribe medication or treatment.
            - For financial topics, provide general insights only, not professional financial advice.
            - Understand that the user may ask about any Nexora module.

            User's message:

            %s

            """.formatted(userMessage);

        return sendAIRequest(prompt);
    }
    public String prioritizeTasksFromContext(
            String taskContext
    ) {

        if (
                taskContext == null
                        || taskContext.isBlank()
        ) {

            return "No task information is available.";
        }


        String prompt = """

            You are Nexora AI, an intelligent task management assistant.

            Analyze the user's task list and identify the most important tasks.

            Consider:
            - Due date
            - Current priority
            - Current status
            - Urgency
            - Whether the task is already completed

            Important rules:
            - Do not invent tasks.
            - Use only the task information provided.
            - Ignore completed tasks when recommending pending work.
            - Recommend the most urgent and important tasks first.
            - Give a clear and practical response.
            - Mention the task title exactly as provided.

            User's tasks:

            %s

            Provide:
            1. The most important task to do first
            2. Other important tasks in order
            3. A short reason for the ordering
            4. A practical action plan for today

            """.formatted(taskContext);


        return sendAIRequest(prompt);
    }
    // CENTRAL AI - TASK PRIORITIZATION FROM DATABASE

    public String prioritizeLoggedInUserTasks() {

        User user =
                userRepository
                        .findByEmail(
                                getLoggedInUserEmail()
                        )
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "User not found"
                                )
                        );


        List<Task> tasks =
                taskRepository
                        .findByUserOrderByDueDateAsc(
                                user,
                                PageRequest.of(
                                        0,
                                        20
                                )
                        )
                        .getContent();


        if (tasks.isEmpty()) {

            return "You currently have no tasks to prioritize.";
        }


        StringBuilder taskContext =
                new StringBuilder();


        for (Task task : tasks) {

            taskContext
                    .append("Task Title: ")
                    .append(task.getTitle())
                    .append("\n");

            taskContext
                    .append("Description: ")
                    .append(
                            task.getDescription() != null
                                    ? task.getDescription()
                                    : "No description"
                    )
                    .append("\n");

            taskContext
                    .append("Status: ")
                    .append(task.getStatus())
                    .append("\n");

            taskContext
                    .append("Priority: ")
                    .append(task.getPriority())
                    .append("\n");

            taskContext
                    .append("Due Date: ")
                    .append(
                            task.getDueDate() != null
                                    ? task.getDueDate()
                                    : "No due date"
                    )
                    .append("\n");

            taskContext
                    .append("--------------------")
                    .append("\n");
        }


        return prioritizeTasksFromContext(
                taskContext.toString()
        );
    }

    // GET LOGGED-IN USER EMAIL FROM JWT

    private String getLoggedInUserEmail() {

        return org.springframework.security
                .core.context.SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();
    }
}