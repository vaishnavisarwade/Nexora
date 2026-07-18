package com.vaishnavi.nexora.ai.controller;

import com.vaishnavi.nexora.ai.dto.AIResponse;
import com.vaishnavi.nexora.ai.service.AIService;
import com.vaishnavi.nexora.document.entity.Document;
import com.vaishnavi.nexora.document.service.DocumentService;
import com.vaishnavi.nexora.document.service.DocumentTextExtractionService;
import com.vaishnavi.nexora.entity.User;
import com.vaishnavi.nexora.exception.ResourceNotFoundException;
import com.vaishnavi.nexora.expense.entity.Expense;
import com.vaishnavi.nexora.expense.entity.Note;
import com.vaishnavi.nexora.expense.repository.ExpenseRepository;
import com.vaishnavi.nexora.grocery.entity.Grocery;
import com.vaishnavi.nexora.grocery.repository.GroceryRepository;
import com.vaishnavi.nexora.health.entity.DietRecord;
import com.vaishnavi.nexora.health.entity.HealthRecord;
import com.vaishnavi.nexora.health.repository.DietRepository;
import com.vaishnavi.nexora.health.repository.HealthRepository;
import com.vaishnavi.nexora.repository.NoteRepository;
import com.vaishnavi.nexora.repository.UserRepository;
import com.vaishnavi.nexora.task.entity.Task;
import com.vaishnavi.nexora.task.repository.TaskRepository;
import com.vaishnavi.nexora.ai.dto.AIChatRequest;
import com.vaishnavi.nexora.ai.dto.AIChatResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ai")
public class AIController {


    private final AIService aiService;
    private final NoteRepository noteRepository;
    private final UserRepository userRepository;
    private final TaskRepository taskRepository;
    private final ExpenseRepository expenseRepository;
    private final GroceryRepository groceryRepository;
    private final DietRepository dietRepository;
    private final HealthRepository healthRepository;
    private final DocumentService documentService;
    private final DocumentTextExtractionService documentTextExtractionService;


    public AIController(
            AIService aiService,
            NoteRepository noteRepository,
            UserRepository userRepository,
            TaskRepository taskRepository,
            ExpenseRepository expenseRepository,
            GroceryRepository groceryRepository,
            DietRepository dietRepository,
            HealthRepository healthRepository,
            DocumentService documentService,
            DocumentTextExtractionService documentTextExtractionService
    ) {

        this.aiService = aiService;
        this.noteRepository = noteRepository;
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
        this.expenseRepository = expenseRepository;
        this.groceryRepository = groceryRepository;
        this.dietRepository = dietRepository;
        this.healthRepository = healthRepository;
        this.documentService = documentService;
        this.documentTextExtractionService =
                documentTextExtractionService;
    }


        // =====================================================
// NOTE AI - SUMMARIZATION
// =====================================================

    @PostMapping("/notes/{id}/summarize")
    public AIResponse summarizeNote(
            @PathVariable Long id,
            Authentication authentication
    ) {

        User user = getAuthenticatedUser(authentication);

        Note note = noteRepository
                .findByIdAndUser(id, user)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                "Note not found"
                        )
                );

        String summary =
                aiService.summarizeNote(
                        note.getContent()
                );

        return processAIResponse(
                summary,
                "Note summarized successfully.",
                "Unable to generate summary."
        );
    }


        // =====================================================
// TASK AI - PRIORITIZATION
// =====================================================


    @PostMapping("/tasks/{id}/prioritize")
    public AIResponse prioritizeTask(
            @PathVariable Long id,
            Authentication authentication
    ) {

        User user = getAuthenticatedUser(authentication);

        Task task = taskRepository
                .findByIdAndUser(id, user)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                "Task not found"
                        )
                );

        String recommendation =
                aiService.prioritizeTask(
                        task.getTitle(),
                        task.getDescription(),
                        task.getStatus().name(),
                        task.getPriority().name(),
                        task.getDueDate() != null
                                ? task.getDueDate().toString()
                                : null
                );

        return processAIResponse(
                recommendation,
                "Task analyzed successfully.",
                "Unable to analyze task."
        );
    }


        // =====================================================
// EXPENSE AI - INSIGHTS
// =====================================================

    @PostMapping("/expenses/{id}/analyze")
    public AIResponse analyzeExpense(
            @PathVariable Long id,
            Authentication authentication
    ) {

        User user = getAuthenticatedUser(authentication);

        Expense expense =
                expenseRepository
                        .findByIdAndUser(id, user)
                        .orElseThrow(
                                () -> new ResourceNotFoundException(
                                        "Expense not found"
                                )
                        );

        String insight =
                aiService.analyzeExpense(
                        expense.getTitle(),
                        expense.getAmount().toString(),
                        expense.getCategory(),
                        expense.getExpenseDate().toString(),
                        expense.getPaymentMethod(),
                        expense.getDescription()
                );

        return processAIResponse(
                insight,
                "Expense analyzed successfully.",
                "Unable to analyze expense."
        );
    }


        // =====================================================
// GROCERY AI - SMART SUGGESTIONS
// =====================================================

    @PostMapping("/groceries/{id}/analyze")
    public AIResponse analyzeGrocery(
            @PathVariable Long id,
            Authentication authentication
    ) {

        User user = getAuthenticatedUser(authentication);

        Grocery grocery =
                groceryRepository
                        .findByIdAndUser(id, user)
                        .orElseThrow(
                                () -> new ResourceNotFoundException(
                                        "Grocery item not found"
                                )
                        );

        String suggestion =
                aiService.analyzeGrocery(
                        grocery.getItemName(),

                        grocery.getQuantity() != null
                                ? grocery.getQuantity().toString()
                                : null,

                        grocery.getUnit() != null
                                ? grocery.getUnit().name()
                                : null,

                        grocery.getCategory(),

                        grocery.getStatus().name(),

                        grocery.getPriority().name(),

                        grocery.getEstimatedPrice() != null
                                ? grocery.getEstimatedPrice().toString()
                                : null,

                        grocery.getActualPrice() != null
                                ? grocery.getActualPrice().toString()
                                : null
                );

        return processAIResponse(
                suggestion,
                "Grocery item analyzed successfully.",
                "Unable to analyze grocery item."
        );
    }

        // =====================================================
// DIET AI - NUTRITION INSIGHTS
// =====================================================


    @PostMapping("/diet/{id}/analyze")
    public AIResponse analyzeDiet(
            @PathVariable Long id,
            Authentication authentication
    ) {

        User user = getAuthenticatedUser(authentication);

        DietRecord dietRecord =
                dietRepository
                        .findById(id)
                        .orElseThrow(
                                () -> new ResourceNotFoundException(
                                        "Diet record not found"
                                )
                        );

        if (!dietRecord.getUser()
                .getId()
                .equals(user.getId())) {

            throw new ResourceNotFoundException(
                    "Diet record not found"
            );
        }

        String insight =
                aiService.analyzeDiet(
                        dietRecord.getMealType(),
                        dietRecord.getFoodName(),
                        dietRecord.getQuantity(),

                        dietRecord.getCalories() != null
                                ? dietRecord.getCalories().toString()
                                : null,

                        dietRecord.getProtein() != null
                                ? dietRecord.getProtein().toString()
                                : null,

                        dietRecord.getCarbs() != null
                                ? dietRecord.getCarbs().toString()
                                : null,

                        dietRecord.getFats() != null
                                ? dietRecord.getFats().toString()
                                : null,

                        dietRecord.getMealTime() != null
                                ? dietRecord.getMealTime().toString()
                                : null
                );

        return processAIResponse(
                insight,
                "Diet record analyzed successfully.",
                "Unable to analyze diet record."
        );
    }


        // =====================================================
// HEALTH AI - WELLNESS INSIGHTS
// =====================================================


    @PostMapping("/health/{id}/analyze")
    public AIResponse analyzeHealth(
            @PathVariable Long id,
            Authentication authentication
    ) {

        User user = getAuthenticatedUser(authentication);

        HealthRecord healthRecord =
                healthRepository
                        .findById(id)
                        .orElseThrow(
                                () -> new ResourceNotFoundException(
                                        "Health record not found"
                                )
                        );

        if (!healthRecord.getUser()
                .getId()
                .equals(user.getId())) {

            throw new ResourceNotFoundException(
                    "Health record not found"
            );
        }

        String insight =
                aiService.analyzeHealth(
                        healthRecord.getWeight() != null
                                ? healthRecord.getWeight().toString()
                                : null,

                        healthRecord.getHeight() != null
                                ? healthRecord.getHeight().toString()
                                : null,

                        healthRecord.getBmi() != null
                                ? healthRecord.getBmi().toString()
                                : null,

                        healthRecord.getWaterIntake() != null
                                ? healthRecord.getWaterIntake().toString()
                                : null,

                        healthRecord.getSleepHours() != null
                                ? healthRecord.getSleepHours().toString()
                                : null,

                        healthRecord.getSteps() != null
                                ? healthRecord.getSteps().toString()
                                : null,

                        healthRecord.getExerciseMinutes() != null
                                ? healthRecord.getExerciseMinutes().toString()
                                : null,

                        healthRecord.getRecordDate() != null
                                ? healthRecord.getRecordDate().toString()
                                : null
                );

        return processAIResponse(
                insight,
                "Health record analyzed successfully.",
                "Unable to analyze health record."
        );
    }

        // =====================================================
// DOCUMENT AI - DOCUMENT ANALYSIS
// =====================================================

    @PostMapping("/documents/{id}/analyze")
    public AIResponse analyzeDocument(
            @PathVariable Long id,
            Authentication authentication
    ) {

        Document document =
                documentService.getDocumentEntity(
                        id,
                        authentication
                );

        String extractedText =
                documentTextExtractionService.extractText(
                        document
                );

        if (
                extractedText == null
                        || extractedText.isBlank()
        ) {

            return failureResponse(
                    "Unable to extract text from document."
            );
        }

        String analysis =
                aiService.analyzeDocument(
                        document.getTitle(),
                        document.getCategory(),
                        document.getDescription(),
                        document.getFileType(),
                        extractedText
                );

        return processAIResponse(
                analysis,
                "Document analyzed successfully.",
                "Unable to analyze document."
        );
    }


        // =====================================================
// COMMON HELPER METHODS
// =====================================================

    private User getAuthenticatedUser(
            Authentication authentication
    ) {

        return userRepository
                .findByEmail(authentication.getName())
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                "User not found"
                        )
                );
    }


    private AIResponse processAIResponse(
            String response,
            String successMessage,
            String unableMessage
    ) {

        if (
                response == null
                        || response.isBlank()
        ) {

            return failureResponse(
                    unableMessage
            );
        }


        if (
                response.startsWith(
                        "AI service quota"
                )
        ) {

            return failureResponse(
                    "AI quota is currently unavailable."
            );
        }


        if (
                response.startsWith(
                        "AI service authentication"
                )
        ) {

            return failureResponse(
                    "AI service authentication failed."
            );
        }


        if (
                response.startsWith(
                        "Unable"
                )
        ) {

            return failureResponse(
                    unableMessage
            );
        }


        if (
                response.startsWith(
                        "Invalid request"
                )
        ) {

            return failureResponse(
                    "Invalid request sent to the AI service."
            );
        }


        if (
                response.startsWith(
                        "AI service error"
                )
        ) {

            return failureResponse(
                    "AI service error. Please try again later."
            );
        }


        return new AIResponse(
                true,
                successMessage,
                response
        );
    }


    private AIResponse failureResponse(
            String message
    ) {

        return new AIResponse(
                false,
                message,
                null
        );
    }
    @PostMapping("/chat")
    public ResponseEntity<AIChatResponse> centralChat(
            @Valid @RequestBody AIChatRequest request
    ) {

        String response =
                aiService.centralChat(request.getMessage());

        return ResponseEntity.ok(
                new AIChatResponse(
                        true,
                        "Central AI response generated successfully.",
                        response
                )
        );
    }
    @GetMapping("/tasks/prioritize")
    public ResponseEntity<AIResponse> prioritizeMyTasks() {

        String response =
                aiService.prioritizeLoggedInUserTasks();

        return ResponseEntity.ok(
                processAIResponse(
                        response,
                        "Tasks prioritized successfully.",
                        "Unable to prioritize your tasks."
                )
        );
    }

}
