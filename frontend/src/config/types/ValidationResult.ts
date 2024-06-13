import ValidationFailed from "./ValidationFailed";
import ValidationSuccess from "./ValidationSuccess";

type ValidationResult = ValidationSuccess | ValidationFailed;

export default ValidationResult;
