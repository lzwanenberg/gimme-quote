import { mock } from "ts-mockito";
import EnvironmentVariables from "./EnvironmentVariables";
import validateEnvironmentVariables from "./validateEnvironmentVariables";
import loadEnvironmentVariables from "./loadEnvironmentVariables";
import InvalidConfigurationError from "./InvalidConfigurationError";

jest.mock('./validateEnvironmentVariables');
const mockValidateEnvironmentVariables = jest.mocked(validateEnvironmentVariables);

describe("loadEnvironmentVariables", () => {
    const ORIGINAL_PROCESS_ENV = process.env;

    beforeEach(() => {
        jest.resetModules();
        process.env = {
            ...ORIGINAL_PROCESS_ENV,
            EXAMPLE_ENVIRONMENT_VARIABLE: "my_value"
        };
    });

    afterAll(() => {
        process.env = ORIGINAL_PROCESS_ENV;
    });

    it("calls validateEnvironmentVariables with process environment", () => {
        mockValidateEnvironmentVariables.mockReturnValue({
            success: true,
            data: mock<EnvironmentVariables>()
        });

        loadEnvironmentVariables();

        expect(mockValidateEnvironmentVariables).toHaveBeenCalledTimes(1);
        const receivedArgument = mockValidateEnvironmentVariables.mock.calls[0][0];
        expect(receivedArgument).toEqual(process.env);
        expect(receivedArgument.EXAMPLE_ENVIRONMENT_VARIABLE).toEqual("my_value");
    });

    describe("when validateEnvironmentVariables returns validation success", () => {
        it("returns data", () => {
            const data = mock<EnvironmentVariables>(jest.fn());
            mockValidateEnvironmentVariables.mockReturnValue({ success: true, data });

            const result = loadEnvironmentVariables();

            expect(result).toEqual(data);
        });
    });


    describe("when validateEnvironmentVariables returns validation error", () => {
        it("returns data", () => {
            mockValidateEnvironmentVariables.mockReturnValue({
                success: false,
                error: "Something went wrong"
            });

            expect.hasAssertions();

            try {
                loadEnvironmentVariables();
            } catch (e) {
                expect(e).toBeInstanceOf(InvalidConfigurationError);
                if (!(e instanceof InvalidConfigurationError)) return;
                expect(e.message).toEqual(
                    "Invalid environment variables configuration:\n" +
                    "Something went wrong");
            }
        });
    });
});
