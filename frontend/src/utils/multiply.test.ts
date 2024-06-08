import multiply from "./multiply";

describe("multiply", () => {
    test("returns multiplied value", () => {
        const a = 3;
        const b = 7;

        const actual = multiply(a, b);

        expect(actual).toEqual(21);
    });
});
