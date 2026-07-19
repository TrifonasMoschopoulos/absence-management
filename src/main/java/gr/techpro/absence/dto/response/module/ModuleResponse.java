package gr.techpro.absence.dto.response.module;

import gr.techpro.absence.enums.Semester;
import io.swagger.v3.oas.annotations.media.Schema;


@Schema(description="Module details response containing all available information about a module")
public class ModuleResponse {
    
    @Schema(description="Surrogate key", example="133")
    private Long id;

    @Schema(description="Unique human-readable code for the module", example="CS101")
    private String code;

    @Schema(example="A brief introduction to computer science")
    private String title;
    
    @Schema(description="Academic units describing the effort required to complete the course", example="6")
    private int credits;
    
    @Schema(description="Academic semester. Accepted values: 'FALL', 'SPRING', 'SUMMER'", example="SPRING")
    private Semester semester;
    
    @Schema(description="The year the course takes place", example="2025")
    private int academicYear;

    public ModuleResponse() {}

    public ModuleResponse(Long id, String code, String title, int credits, Semester semester, int academicYear){
        this.id = id;
        this.code = code;
        this.title = title;
        this.credits = credits;
        this.semester = semester;
        this.academicYear = academicYear;
    }

    public Long getId() { return this.id; }
    public String getCode() { return this.code; }
    public String getTitle() { return this.title; }
    public int getCredits() { return this.credits; }
    public Semester getSemester() { return this.semester; }
    public int getAcademicYear() { return this.academicYear; }
}
