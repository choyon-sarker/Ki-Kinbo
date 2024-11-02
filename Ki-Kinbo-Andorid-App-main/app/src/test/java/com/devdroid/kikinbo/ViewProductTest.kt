//import android.widget.TextView
//import com.devdroid.kikinbo.view.ViewProduct
//import com.devdroid.kikinbo.model.ProductDataModel
//import org.junit.jupiter.api.Assertions.assertEquals
//import org.junit.jupiter.api.BeforeEach
//import org.junit.jupiter.api.Test
//import org.junit.jupiter.api.extension.ExtendWith
//import org.mockito.ArgumentMatchers.any
//import org.mockito.InjectMocks
//import org.mockito.Mock
//import org.mockito.Mockito.*
//import org.mockito.junit.jupiter.MockitoExtension
//
//@ExtendWith(MockitoExtension::class)
//class ViewProductTest<ProductViewModelInterface : Any> {
//
//    private lateinit var viewProduct: ViewProduct
//    private lateinit var mockProductViewModel: ProductViewModelInterface
//    private lateinit var productNameId: TextView
//    private lateinit var productPriceId: TextView
//    private lateinit var productRatingId: TextView
//    private lateinit var productCategoryId: TextView
//    private lateinit var productDetailsId: TextView
//
//    @BeforeEach
//    fun setUp() {
//        viewProduct = ViewProduct()
//        mockProductViewModel = mock(ProductViewModelInterface::class.java)
//        viewProduct.productViewModel = mockProductViewModel
//
//        productNameId = mock(TextView::class.java)
//        productPriceId = mock(TextView::class.java)
//        productRatingId = mock(TextView::class.java)
//        productCategoryId = mock(TextView::class.java)
//        productDetailsId = mock(TextView::class.java)
//
//        viewProduct.onCreate(null) // Simulate onCreate
//
//        // Set the mocked TextViews in the activity
//        viewProduct.productNameId = productNameId
//        viewProduct.productPriceId = productPriceId
//        viewProduct.productRatingId = productRatingId
//        viewProduct.productCategoryId = productCategoryId
//        viewProduct.productDetailsId = productDetailsId
//    }
//
//    @Test
//    fun testSuccessfulDisplayOfProductDetails() {
//        val sampleProduct = ProductDataModel(
//            productId = "1",
//            productName = "Test Product",
//            productPrice = 100,
//            productRating = 4.5,
//            productDetail = "This is a test product.",
//            productReview = "Good product.",
//            productCategory = "Electronics"
//        )
//        `when`(mockProductViewModel.fetchProductData("1", any())).thenAnswer {
//            val callback = it.arguments[1] as (ProductDataModel?) -> Unit
//            callback(sampleProduct)
//        }
//
//        viewProduct.fetchAndDisplayProduct()
//
//        assertEquals("Test Product", productNameId.text)
//        assertEquals("100.0", productPriceId.text)
//        assertEquals("4.5", productRatingId.text)
//        assertEquals("Electronics", productCategoryId.text)
//        assertEquals("This is a test product.", productDetailsId.text)
//    }
//
//    @Test
//    fun testDisplayOfNullProduct() {
//        `when`(mockProductViewModel.fetchProductData("1", any())).thenAnswer {
//            val callback = it.arguments[1] as (ProductDataModel?) -> Unit
//            callback(null)
//        }
//
//        viewProduct.fetchAndDisplayProduct()
//
//        assertEquals("", productNameId.text)
//        assertEquals("", productPriceId.text)
//        assertEquals("", productRatingId.text)
//        assertEquals("", productCategoryId.text)
//        assertEquals("", productDetailsId.text)
//    }
//
//    @Test
//    fun testDisplayOfEmptyProductName() {
//        val sampleProduct = ProductDataModel(
//            productId = "1",
//            productName = "",
//            productPrice = 100,
//            productRating = 4.5,
//            productDetail = "This is a test product.",
//            productReview = "Good product.",
//            productCategory = "Electronics"
//        )
//        `when`(mockProductViewModel.fetchProductData("1", any())).thenAnswer {
//            val callback = it.arguments[1] as (ProductDataModel?) -> Unit
//            callback(sampleProduct)
//        }
//
//        viewProduct.fetchAndDisplayProduct()
//
//        assertEquals("", productNameId.text)
//        assertEquals("100.0", productPriceId.text)
//        assertEquals("4.5", productRatingId.text)
//        assertEquals("Electronics", productCategoryId.text)
//        assertEquals("This is a test product.", productDetailsId.text)
//    }
//
//    @Test
//    fun testDisplayOfZeroProductPrice() {
//        val sampleProduct = ProductDataModel(
//            productId = "1",
//            productName = "Test Product",
//            productPrice = 0,
//            productRating = 4.5,
//            productDetail = "This is a test product.",
//            productReview = "Good product.",
//            productCategory = "Electronics"
//        )
//        `when`(mockProductViewModel.fetchProductData("1", any())).thenAnswer {
//            val callback = it.arguments[1] as (ProductDataModel?) -> Unit
//            callback(sampleProduct)
//        }
//
//        viewProduct.fetchAndDisplayProduct()
//
//        assertEquals("Test Product", productNameId.text)
//        assertEquals("0", productPriceId.text) // Check for zero price
//        assertEquals("4.5", productRatingId.text)
//        assertEquals("Electronics", productCategoryId.text)
//        assertEquals("This is a test product.", productDetailsId.text)
//    }
//
//    @Test
//    fun testDisplayOfMinimumProductRating() {
//        val sampleProduct = ProductDataModel(
//            productId = "1",
//            productName = "Test Product",
//            productPrice = 100,
//            productRating = 0.0, // Minimum rating
//            productDetail = "This is a test product.",
//            productReview = "Good product.",
//            productCategory = "Electronics"
//        )
//        `when`(mockProductViewModel.fetchProductData("1", any())).thenAnswer {
//            val callback = it.arguments[1] as (ProductDataModel?) -> Unit
//            callback(sampleProduct)
//        }
//
//        viewProduct.fetchAndDisplayProduct()
//
//        assertEquals("0.0", productRatingId.text) // Check for minimum rating
//    }
//
//    @Test
//    fun testDisplayOfMaximumProductRating() {
//        val sampleProduct = ProductDataModel(
//            productId = "1",
//            productName = "Test Product",
//            productPrice = 100,
//            productRating = 5.0, // Maximum rating
//            productDetail = "This is a test product.",
//            productReview = "Good product.",
//            productCategory = "Electronics"
//        )
//        `when`(mockProductViewModel.fetchProductData("1", any())).thenAnswer {
//            val callback = it.arguments[1] as (ProductDataModel?) -> Unit
//            callback(sampleProduct)
//        }
//
//        viewProduct.fetchAndDisplayProduct()
//
//        assertEquals("5.0", productRatingId.text) // Check for maximum rating
//    }
//
//    @Test
//    fun testHandlingOfProductDetailAsEmptyString() {
//        val sampleProduct = ProductDataModel(
//            productId = "1",
//            productName = "Test Product",
//            productPrice = 100,
//            productRating = 4.5,
//            productDetail = "", // Empty product detail
//            productReview = "Good product.",
//            productCategory = "Electronics"
//        )
//        `when`(mockProductViewModel.fetchProductData("1", any())).thenAnswer {
//            val callback = it.arguments[1] as (ProductDataModel?) -> Unit
//            callback(sampleProduct)
//        }
//
//        viewProduct.fetchAndDisplayProduct()
//
//        assertEquals("", productDetailsId.text) // Check for empty product detail
//    }
//
//    @Test
//    fun testHandlingOfProductReviewAsNull() {
//        val sampleProduct = ProductDataModel(
//            productId = "1",
//            productName = "Test Product",
//            productPrice = 100,
//            productRating = 4.5,
//            productDetail = "This is a test product.",
//            productReview = null, // Null product review
//            productCategory = "Electronics"
//        )
//        `when`(mockProductViewModel.fetchProductData("1", any())).thenAnswer {
//            val callback = it.arguments[1] as (ProductDataModel?) -> Unit
//            callback(sampleProduct)
//        }
//
//        viewProduct.fetchAndDisplayProduct()
//
//        assertEquals("", productDetailsId.text) // Assuming the UI handles null gracefully
//    }
//
//    @Test
//    fun testFetchingNonExistentProduct() {
//        `when`(mockProductViewModel.fetchProductData("999", any())).thenAnswer {
//            val callback = it.arguments[1] as (ProductDataModel?) -> Unit
//            callback(null)
//        }
//
//        viewProduct.fetchAndDisplayProduct()
//
//        assertEquals("", productNameId.text) // Ensure UI handles non-existent product
//    }
//
//    @Test
//    fun testUIResetsOnNewFetch() {
//        val firstProduct = ProductDataModel(
//            productId = "1",
//            productName = "First Product",
//            productPrice = 100,
//            productRating = 4.5,
//            productDetail = "First product detail.",
//            productReview = "Good product.",
//            productCategory = "Electronics"
//        )
//        `when`(mockProductViewModel.fetchProductData("1", any())).thenAnswer {
//            val callback = it.arguments[1] as (ProductDataModel?) -> Unit
//            callback(firstProduct)
//        }
//
//        viewProduct.fetchAndDisplayProduct()
//
//        assertEquals("First Product", productNameId.text)
//
//        // Now fetch a new product
//        val secondProduct = ProductDataModel(
//            productId = "2",
//            productName = "Second Product",
//            productPrice = 200,
//            productRating = 5.0,
//            productDetail = "Second product detail.",
//            productReview = "Excellent product.",
//            productCategory = "Home Appliances"
//        )
//        `when`(mockProductViewModel.fetchProductData("2", any())).thenAnswer {
//            val callback = it.arguments[1] as (ProductDataModel?) -> Unit
//            callback(secondProduct)
//        }
//
//        viewProduct.fetchAndDisplayProduct()
//
//        // Verify that UI has updated to the new product
//        assertEquals("Second Product", productNameId.text)
//        assertEquals("200.0", productPriceId.text)
//        assertEquals("5.0", productRatingId.text)
//        assertEquals("Home Appliances", productCategoryId.text)
//        assertEquals("Second product detail.", productDetailsId.text)
//    }
//}
