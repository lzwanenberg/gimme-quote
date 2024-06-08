import EnvironmentVariables from "./EnvironmentVariables";
import validateEnvironmentVariables from "./validateEnvironmentVariables";

const validEnvironmentVariables: EnvironmentVariables = {
    QUOTE_SERVICE_HOST: "localhost",
    QUOTE_SERVICE_PORT: 8080
};

describe("validateEnvironmentVariables", () => {
    describe("valid environment variables", () => {
        test("#success is true", () => {
            const variables = { ...validEnvironmentVariables };

            const result = validateEnvironmentVariables(variables);

            expect(result.success).toBe(true);
        });

        test("#result is equal to provided variables", () => {
            const variables = { ...validEnvironmentVariables };

            const result = validateEnvironmentVariables(variables);

            if (!result.success) fail('expected success');
            expect(result.data).toEqual(variables);
        });
    });

    describe("QUOTE_SERVICE_HOST", () => {
        test("fails when missing", () => {
            const variables: any = { ...validEnvironmentVariables };
            delete variables.QUOTE_SERVICE_HOST;

            const result = validateEnvironmentVariables(variables);

            expect(result.success).toBe(false);
            if (result.success) fail();

            expect(result.error).toMatchInlineSnapshot(`"{"_errors":[],"QUOTE_SERVICE_HOST":{"_errors":["Required"]}}"`);
        });

        test("fails when not a string", () => {
            const variables: any = { ...validEnvironmentVariables, QUOTE_SERVICE_HOST: 123 };

            const result = validateEnvironmentVariables(variables);

            expect(result.success).toBe(false);
            if (result.success) fail();

            expect(result.error).toMatchInlineSnapshot(`"{"_errors":[],"QUOTE_SERVICE_HOST":{"_errors":["Expected string, received number"]}}"`);
        });
    });

    describe("QUOTE_SERVICE_PORT", () => {
        test("fails when missing", () => {
            const variables: any = { ...validEnvironmentVariables };
            delete variables.QUOTE_SERVICE_PORT;

            const result = validateEnvironmentVariables(variables);

            expect(result.success).toBe(false);
            if (result.success) fail();

            expect(result.error).toMatchInlineSnapshot(`"{"_errors":[],"QUOTE_SERVICE_PORT":{"_errors":["Required"]}}"`);
        });

        test("fails when not a number", () => {
            const variables: any = { ...validEnvironmentVariables, QUOTE_SERVICE_PORT: "not-a-number" };

            const result = validateEnvironmentVariables(variables);

            expect(result.success).toBe(false);
            if (result.success) fail();

            expect(result.error).toMatchInlineSnapshot(`"{"_errors":[],"QUOTE_SERVICE_PORT":{"_errors":["Expected number, received string"]}}"`);
        });

        test('succeeds when string representing number', () => {
            const variables: any = { ...validEnvironmentVariables, QUOTE_SERVICE_PORT: "8080" };

            const result = validateEnvironmentVariables(variables);

            expect(result.success).toBe(true);
            if (!result.success) fail();

            expect(result.data.QUOTE_SERVICE_PORT).toEqual(8080);
        });

        test("fails when not a positive number", () => {
            const variables: any = { ...validEnvironmentVariables, QUOTE_SERVICE_PORT: -10 };

            const result = validateEnvironmentVariables(variables);

            expect(result.success).toBe(false);
            if (result.success) fail();

            expect(result.error).toMatchInlineSnapshot(`"{"_errors":[],"QUOTE_SERVICE_PORT":{"_errors":["Number must be greater than 0"]}}"`);
        });
    });
});
