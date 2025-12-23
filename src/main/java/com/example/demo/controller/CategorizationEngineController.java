@RestController
@RequestMapping("/api/engine")
public class CategorizationEngineController {

    @Autowired
    private CategorizationEngineService engineService;

    @GetMapping("/categorize/{id}")
    public ResponseEntity<?> categorize(@PathVariable Long id) {
        return ResponseEntity.ok(engineService.categorizeTicket(id));
    }
}
