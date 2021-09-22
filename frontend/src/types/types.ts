export type Product = {
    id: number
    title: string
    producer: string
    year: number
    country: string
    type: string
    volume: string
    productGender: string
    productTopNotes: string
    productMiddleNotes: string
    productBaseNotes: string
    description: string
    filename: string
    price: number
    productRating: number
    reviews: Array<Review>
};

export type ProductRequest = {
    id?: number
    title: string
    producer: string
    year: number
    country: string
    type: string
    productGender: string
    productTopNotes: string
    productMiddleNotes: string
    productBaseNotes: string
    description: string
    filename: string
    price: number
    productRating?: number
};

export type ProductErrors = {
    productTitleError: string
    producerError: string
    yearError: string
    countryError: string
    typeError: string
    descriptionError: string
    productGenderError: string
    productTopNotesError: string
    productMiddleNotesError: string
    productBaseNotesError: string
    priceError: string
    filenameError: string
};

export type Review = {
    id: number
    author: string
    message: string
    rating: number
    date: any
};

export type ReviewData = {
    productId: number | string
    author: string
    message: string
    rating: number
};

export type ReviewError = {
    authorError: string
    messageError: string
    ratingError: string
};

export type Order = {
    id: number
    totalPrice: number
    date: string
    firstName: string
    lastName: string
    city: string
    address: string
    email: string
    phoneNumber: string
    postIndex: number
    orderItems: Array<OrderItem>
};

export type OrderItem = {
    id: number
    amount: number
    quantity: number
    product: Product
};

export type OrderError = {
    emailError: string
    firstNameError: string
    lastNameError: string
    cityError: string
    addressError: string
    postIndexError: string
    phoneNumberError: string
};

export type User = {
    id: number
    email: string
    firstName: string
    lastName: string
    city: string
    address: string
    phoneNumber: string
    postIndex: string
    activationCode: string | null
    passwordResetCode: string | null
    active: boolean
    provider: string
    roles: Array<string>
};

export type UserEdit = {
    id: number | undefined
    firstName: string | undefined
    lastName: string | undefined
    city: string | undefined
    address: string | undefined
    phoneNumber: string | undefined
    postIndex: string | undefined
};

export type UserEditErrors = {
    firstNameError: string
    lastNameError: string
};

export type UserData = {
    email: string
    password: string
};

export type UserRegistration = {
    email: string
    firstName: string
    lastName: string
    password: string
    password2: string
};

export type UserResetPasswordData = {
    email: string | undefined
    password: string
    password2: string
};

export type AuthErrors = {
    emailError: string
    firstNameError: string
    lastNameError: string
    passwordError: string
    password2Error: string
};

export type FilterParamsType = {
    producers: Array<string>
    genders: Array<string>
    prices: Array<number>
    sortByPrice?: boolean
};

export type ProductPrice = {
    id: number
    name: string
    array: Array<number>
};

export type BrandType = {
    name: string
    url: string
};
